# Tab CLI Run

Allows the execution of a number of cli commands present the results in a tabbed interface

- The commands are given as an array in a json file.
- Each command can have different UI settings (std out/err colours and background).
- The commands are executed in different threads.
- A target can be passed as an argument.

## Limitations
- Currently the commands will need to complete before the UI is updated
- There is not way to interact with a command in a tab
- The commands are being displayed in a DUMB terminal.

## Usage
Get the latest release here: [https://github.com/alt236/tabclirun/releases]()

```
usage: tabclirun.jar -s <arg> -t <arg>
version: 1.0.0
Execute a number of CLI commands in a tabbed interface.

 -s,--settings <arg>   The configuration file for the commands to display
                       in each tab.
 -t,--target <arg>     A target for the commands. Use '{param.target}' in
                       the command syntax as a placeholder.

Sample usage:

 * tabclirun.jar --settings ~/tmp/settings.json --target ~/tmp/a_file

Source code: https://github.com/alt236/tabclirun
Please report issues at https://github.com/alt236/tabclirun/issues
```

## Sample settings file

```
{
  "globalSettings": {
    "normalTextColor": "#000ff0",
    "errorTextColor": "#0000ff",
    "consoleColor": "#ffffff"
  },
  "commands": [
    {
      "name": "Dir listing",
      "command": "ls -all \"{param.target}\"",
      "normalTextColor": "#00ff00",
      "errorTextColor": "#ff0000",
      "consoleColor": "#000000"
    },
    {
      "name": "Free space",
      "command": "df"
    },
    {
      "command": "mount"
    }
  ]
}
```

## Build Instructions

Linux/Mac: `mvn clean package && chmod +x target/tabclirun-X.X.jar`

## Versions

* 1.0.0: First release

## Links

* Github: [https://github.com/alt236/tabclirun]()
* Bug reports: [https://github.com/alt236/tabclirun/issues]()
* Releases: [https://github.com/alt236/tabclirun/releases]()

## Credits

* Author: [Alexandros Schillings](https://github.com/alt236).
* Icon: Terminal Icon by [paomedia](http://www.iconarchive.com/show/small-n-flat-icons-by-paomedia/terminal-icon.html)


The code in this project is licensed under the [Apache Software License 2.0](LICENSE).

## License
Copyright (c) 2018-present, Alexandros Schillings.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.