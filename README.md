# TargetProcess Issue Tracker plugin for TeamCity

## Introduction
This project creates a [TeamCity](www.jetbrains.com/teamcity/) Plugin to communicate with [TargetProcess](http://www.targetprocess.com/) issue tracker.<BR>
Tested on TeamCity 9.1.5

## Compile
```
1) Clone the Github repository from "https://github.com/shahabhameed/TeamCity_TargetProcessIntegration/" to any directory or download zip "https://github.com/shahabhameed/TeamCity_TargetProcessIntegration/archive/master.zip".
2) Open any JavaEE IDE and Import it as an existing maven project.
3) Execute "mvn clean package" at the root pom.xml 

```

## Quick-Use
If you just need the zip to activate the plugin take this zip: *target/targetprocess.zip* <BR>

## Installation
To install the plugin login TeamCity with administrator account <BR>
1) Select **Administration->Plugin List** <BR>
2) Click **Upload plugin zip**.<BR>
The server will tell you that the plugin gets uploaded to *<Teamcity Data Directory>\plugins*.
After the plugin is uploaded you have to restart the server.<BR>
(Check the [Documentation](https://confluence.jetbrains.com/display/TCD9/Installing+Additional+Plugins) for manually installing the plugin)

## Building
You'll need JDK 1.7 and Maven (apache-maven-3.3.3-bin) installed and the corresponding environment settings (`JAVA_HOME=<JDK Location>` and `MAVEN_HOME="MAVEN Location`')

Running `mvn clean package` will automatically download all dependencies and will create the zip file in the `target` folder

## Workflow
The plugin reads the json for a specific issue with an InputStream and then parses the text with regular expressions.

Screenshots
-----------

- **Configure Connection**

![alt text](https://github.com/shahabhameed/TeamCity_TargetProcessIntegration/blob/master/screens/screen01.png "Configure Connection")

- **Issue Fetched**

![alt text](https://github.com/shahabhameed/TeamCity_TargetProcessIntegration/blob/master/screens/screen02.png "Issue Fetched")

