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

## Usage
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
When setup run `gradlew tasks` to get a list of available tasks. BlueJ related tasks are in the *BlueJ* group.

## Used by
The following projects are built upon BlueJ Extender:

* **[ASEPSiS-BlueJ](https://github.com/olerass/asepsis-bluej)**<br>Prototype extension that (some day) integrates hand-ins
and feedback directly into the core experience of BlueJ. This is the project from which
BlueJ Extender originates. It includes many examples of best-practices when designing BlueJ extensions,
including how to write effective Cucumber acceptance-tests, how to do proper unit testing, how to extend BlueJ outside
the extension API, and how to design a testable GUI using modern tools and techniques.
