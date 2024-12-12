#!/bin/python
import os
import shutil
import subprocess
from pathlib import Path

_INSTALL_LOCATION = os.path.expanduser("~/installed/tabclirun/")
_SCRIPT_NAME_MAP = {
    "tabclirun-": "tabclirun",
}


def _make_executable(path):
    mode = os.stat(path).st_mode
    mode |= (mode & 0o444) >> 2  # copy R bits to X
    os.chmod(path, mode)


def _change_cwd():
    abspath = os.path.abspath(__file__)
    os.chdir(os.path.dirname(abspath))
    print("Current Directory:", os.getcwd())


def _run_command(command: list[str]):
    str_command = " ".join(command)
    print(f"Executing: '{str_command}'")
    subprocess.run(str_command, check=True, shell=True, executable="/bin/bash")


def _copy_fat_jars(fat_jars: list[str]):
    print(f"Moving {len(fat_jars)} Jars to", _INSTALL_LOCATION)
    os.makedirs(os.path.dirname(_INSTALL_LOCATION), exist_ok=True)
    for jar in fat_jars:
        print("\t", jar)
        shutil.copy2(jar, _INSTALL_LOCATION)


def _get_script_filename_for_jar(jar: Path) -> str:
    jar_file_name = os.path.basename(jar)
    first_dash = jar_file_name.index("-")
    map_key = jar_file_name[0: first_dash + 1]
    return _SCRIPT_NAME_MAP[map_key]


def _create_scripts(fat_jars: list[Path]):
    print(f"Creating run scripts")
    for jar in fat_jars:
        jar_file_name = os.path.basename(jar)
        script_filename = _get_script_filename_for_jar(jar)
        script_path = os.path.join(_INSTALL_LOCATION, script_filename)
        print("\t", script_path)

        with open(script_path, "w") as f:
            script_content = f"""
                #!/bin/sh
                java -jar "{_INSTALL_LOCATION}/{jar_file_name}" "$@"
                """.strip()
            f.write(script_content)
        _make_executable(script_path)


_change_cwd()
_run_command(["./gradlew", "clean", "test"])
_run_command(["./gradlew", "clean", "shadowJar"])

print("\n\n")
fat_jars = list(Path(".").rglob("*-all.jar"))

_copy_fat_jars(fat_jars)
_create_scripts(fat_jars)

print("\nDONE!")
