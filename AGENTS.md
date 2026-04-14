# Repository Guidelines

## Project Structure & Module Organization
This repository contains a single Maven plugin module, `staticweave-maven-plugin`. Source lives under `src/main/java/io/github/xsavikx/eclipselink/mojo`, with the main entry point in `EclipselinkStaticWeaveMojo.java` and supporting logging code in `LogWriter.java`. Build outputs go to `target/`. Maven wrapper files (`mvnw`, `mvnw.cmd`, `.mvn/`) should be used for repeatable local builds. There is currently no `src/test/java` tree; add it when introducing automated tests.

## Build, Test, and Development Commands
Use the Maven wrapper from the repository root:

- `.\mvnw.cmd clean verify` builds the plugin, runs the standard lifecycle, and is the default pre-PR check.
- `.\mvnw.cmd test` runs unit tests only; at present this is mainly a safety check because no tests are committed.
- `.\mvnw.cmd clean package` creates the plugin JAR in `target/`.
- `.\mvnw.cmd help:describe -Dplugin=io.github.xsavikx:staticweave-maven-plugin -Ddetail` inspects generated plugin metadata.
- `.\mvnw.cmd -Dpublish=github deploy` or `.\mvnw.cmd -Dpublish=central deploy` uses the configured release profiles.

## Coding Style & Naming Conventions
The codebase is Java 8 and uses standard Maven plugin annotations from `org.apache.maven.plugins.annotations`. Follow the existing style: 2-space indentation in Java, braces on the same line, `UpperCamelCase` for classes, `lowerCamelCase` for fields and methods, and package names under `io.github.xsavikx.eclipselink.mojo`. Keep Mojo parameters explicit with `@Parameter` and prefer short, targeted log messages through Maven's logger.

## Testing Guidelines
When adding tests, place them in `src/test/java` mirroring the production package layout. Prefer JUnit-based tests for isolated behavior and verify error handling around classpath construction, logging levels, and weaving configuration. Name test classes `*Test` and run them with `.\mvnw.cmd test`. Run `.\mvnw.cmd clean verify` before opening a pull request.

## Commit & Pull Request Guidelines
Recent history uses short, imperative commit subjects such as `Update Maven wrapper, workflows, dependencies, and plugins versions` and `Fixate plugin versions`. Keep subjects concise, capitalized, and action-oriented. Pull requests should describe the behavior change, note any dependency or plugin-version updates, reference related issues, and include the exact Maven command used for verification.
