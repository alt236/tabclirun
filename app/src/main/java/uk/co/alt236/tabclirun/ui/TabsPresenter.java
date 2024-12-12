package uk.co.alt236.tabclirun.ui;

import dev.alt236.tabclirun.libs.config.Command;
import dev.alt236.tabclirun.libs.exec.CommandExecutor;
import dev.alt236.tabclirun.libs.exec.result.Result;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import uk.co.alt236.tabclirun.resources.Resources;

import java.util.List;

public class TabsPresenter {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final Resources resources;
    private final boolean verbose;

    private boolean presenting;

    public TabsPresenter(Resources resources, boolean verbose) {
        this.verbose = verbose;
        this.resources = resources;
    }

    public void present(List<Command> commandList) {
        if (presenting) {
            throw new IllegalStateException("Already presenting!");
        }

        presenting = true;

        final View view = new View(resources);
        view.display();

        for (final Command command : commandList) {
            view.addTab(command);
        }

        executeCommands(view, commandList);
    }


    private void executeCommands(final View view,
                                 final List<Command> commands) {

        final CommandExecutor executor = new CommandExecutor(verbose);

        for (final Command command : commands) {
            final Single<Result> observable =
                    Single.fromCallable(() -> executor.executeCommand(command.getCommand()));

            final Disposable disposable = observable
                    .subscribeOn(Schedulers.io())
                    .subscribe(result -> {
                        int tabIndex = view.findTabWithName(command.getName());
                        view.setTabData(tabIndex, result);
                    });

            compositeDisposable.add(disposable);
        }
    }
}
