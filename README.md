BlueJ Extender
======
BlueJ Extender is a modern framework for developing BlueJ extensions. By leveraging the powerful
[Gradle](http://www.gradle.org/) build-automation framework BlueJ extender gives you the tools you need to develop
BlueJ extensions as quickly as possible by increasing the focus on the maintainability and portability quality attributes
of your extension. Because, remember, [the only way to go fast is to go well](http://butunclebob.com/ArticleS.UncleBob.VehementMediocrity).

## Features
Notable features:

* Out-of-the-box support for [Cucumber](https://github.com/cucumber/cucumber-jvm) end-to-end/acceptance tests that
interact with your extension through the BlueJ GUI like a normal user would (can be disabled completely if need be).
* Integrates with [Travis](https://travis-ci.com/) continuous integration server which allows you to test your extension
on any BlueJ version and any Travis supported JDKs. This of course includes running the end-to-end tests! All this is
done with very few lines of code in a simple configuration file (can be disabled completely if need be).
* One-line command for compiling your extension into a BlueJ-compatible extension JAR archive.
* Support for using any third-party libraries in the BlueJ extension itself as long as they have a separate namespace.
Everything is automatically compiled into the extension archive.
* Easily launch *any* BlueJ version (downloaded if needed) with your extension installed for manual testing.
* Integrates with the well-known [SwingExplorer](http://www.swingexplorer.com/) tool that provides useful utilities for
developing and debugging Swing applications. One-line command for launching *any* BlueJ version with your extension and SwingExplorer.
* Extensible architecture that allows fine-grained customization.

## Usage
Coming soon...

## Used by
The following projects are built upon BlueJ Extender:

* **[ASEPSiS-BlueJ](https://github.com/olerass/asepsis-bluej)**<br>Integrates hand-ins and feedback directly into the core experience of BlueJ. This is the project from which
BlueJ Extender originates. It includes many examples of best-practices when designing BlueJ extensions,
including how to write effective Cucumber acceptance-tests, how to do proper unit testing, how to extend BlueJ outside the extension API,
and how to design a testable GUI using modern tools and techniques.