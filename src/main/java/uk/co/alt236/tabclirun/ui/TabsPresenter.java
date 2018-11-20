package uk.co.alt236.tabclirun.ui;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import uk.co.alt236.tabclirun.Command;
import uk.co.alt236.tabclirun.exec.CommandExecutor;
import uk.co.alt236.tabclirun.exec.Result;

import java.util.List;

public class TabsPresenter {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private boolean presenting;

    public void present(List<Command> commandList) {
        if (presenting) {
            throw new IllegalStateException("Already presenting!");
        }

        presenting = true;

        final View view = new View();
        view.display();

        for (final Command command : commandList) {
            view.addTab(command);
        }

        executeCommands(view, commandList);
    }


    private void executeCommands(final View view,
                                 final List<Command> commands) {

        final CommandExecutor executor = new CommandExecutor();
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
