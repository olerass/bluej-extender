BlueJ Extender
======
BlueJ Extender is a state-of-the-art framework for developing BlueJ extensions. By leveraging the powerful
[Gradle](http://www.gradle.org/) build-automation tool BlueJ Extender gives you the tools you need to quickly develop
high-quality BlueJ extensions. Because remember: [the only way to go fast is to go well](http://butunclebob.com/ArticleS.UncleBob.VehementMediocrity).

## Features
* Easy to use project template that shows how to use acceptance-tests, unit-tests and BlueJ's label mechanism.
* Automatically detects and reports [Swing EDT violations](http://docs.oracle.com/javase/tutorial/uiswing/concurrency/dispatch.html) in your extension.
* Out-of-the-box support for [Cucumber](https://github.com/cucumber/cucumber-jvm) tests that
interact with your extension through the BlueJ GUI like a normal user would (can be disabled completely if need be).
* Integrates with [Travis](https://travis-ci.com/) continuous integration which allows you to easily test your extension
on multiple BlueJ versions and multiple JDKs. This includes running the Cucumber tests in a headless environment. All this is
done with very few lines of code in a simple configuration file (can be disabled completely if need be).
* One-line command for compiling your extension into a BlueJ-compatible extension JAR.
* Transparently explodes third-party libraries into the extension JAR during build and testing.
* Easily launch *any* BlueJ version with your extension installed for manual testing and automatically cleans up afterwards.
* Automatically downloads and extracts any BlueJ version to a local directory.
* Integrates with [SwingExplorer](http://www.swingexplorer.com/) which provides useful utilities for
developing and debugging Swing applications. One-line command for launching *any* BlueJ version with your extension and SwingExplorer.
* Supports all major operating systems: Linux, Windows and OS X.
* Supports all Gradle aware IDEs and tools. See [Gradle Tooling](http://www.gradle.org/tooling) for more information.

## Setup
Clone the project (or use the GitHub download features):
```
git clone https://github.com/olerass/bluej-extender <project name>
```

Then run setup and follow the instructions:

**Linux/OS X**
```
cd <project name>
chmod +x gradlew
./gradlew setup -q
```
**Windows**
```
cd <project name>
gradlew setup -q
```

## Usage
BlueJ Extender provides a number of Gradle tasks for performing the most common operations. All BlueJ related tasks accept a `-PbluejVer=<ver>` argument specifying the BlueJ version to use for the task. For example, `gradle cucumber -PbluejVer=3.1.0` runs all acceptance-tests on a locally downloaded copy of BlueJ 3.1.0. Without the argument the latest version of BlueJ is used unless overriden by the *setDefaultBlueJVer* task.

####Primary tasks:
* `runWithBlueJ [-PbluejVer=<ver>] [-Pswxpl]` <br>Runs the specified (or latest) local version of BlueJ with the extension installed and removes it afterwards. Use the `-Pswxpl` switch to run with SwingExplorer.
* `cucumber [-PbluejVer=<ver>]`<br>Runs all acceptance-tests using the specified (or latest) local version of BlueJ.
* `check [-PbluejVer=<ver>]`<br>Runs all tests including acceptance-tests using the specified (or latest) local version of BlueJ.
* `jar`<br>Assembles a BlueJ compatible extension JAR in the *bluej-extension* subproject build folder.
* `setBlueJBuildVer [-PbluejVer=<ver>]`<br>Sets the BlueJ version to build the extension against to the specified (or latest) version of BlueJ
* `setDefaultBlueJVer [-PbluejVer=<ver>]`<br>Sets the default BlueJ version to use for all tasks to the specified (or latest) version.


####Other tasks:
* `getBlueJ [-PbluejVer=<ver>]`<br>Downloads the specified (or latest) version of BlueJ into the local BlueJ directory *bluej-dist*.
* `installIntoBlueJ [-PbluejVer=<ver>]`<br>Builds and installs the extension into the specified (or latest) local version of BlueJ.
* `uninstallFromBlueJ [-PbluejVer=<ver>] `<br>Removes the extension, if present, from the specified (or latest) local version of BlueJ.

## Travis-CI
Bluej-Extender comes with a `.travis.yml` file preconfigured to run all tests (including acceptance-tests) on the three latest versions of BlueJ. You can take advantage of this by forking or creating your BlueJ-Extender based extension project on GitHub and then [setup Travis integration](http://docs.travis-ci.com/user/getting-started/).

## Used by
The following projects are built upon BlueJ Extender:

* **[ASEPSiS-BlueJ](https://github.com/olerass/asepsis-bluej)**<br>Prototype extension that (some day) integrates hand-ins
and feedback directly into the core experience of BlueJ. This is the project from which
BlueJ Extender originates. It includes many examples of best-practices when designing BlueJ extensions,
including how to write effective Cucumber acceptance-tests, how to do proper unit testing, how to extend BlueJ outside
the extension API, and how to design a testable GUI using modern tools and techniques.