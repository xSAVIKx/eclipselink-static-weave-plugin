# Repository Guidelines

## Project Structure & Module Organization
This repository contains a single Maven plugin module, `staticweave-maven-plugin`. Source lives under `src/main/java/io/github/xsavikx/eclipselink/mojo`, with the main entry point in `EclipselinkStaticWeaveMojo.java` and supporting logging code in `LogWriter.java`. Maven integration-test fixtures live under `src/it/` and cover EclipseLink `2.7.16`, `3.0.4`, and `4.0.9`. Build outputs go to `target/`. Maven wrapper files (`mvnw`, `mvnw.cmd`, `.mvn/`) should be used for repeatable local builds.

## Build, Test, and Development Commands
Use the Maven wrapper from the repository root:

- `.\mvnw.cmd clean verify` builds the plugin, runs the standard lifecycle plus invoker-based compatibility tests, and is the default pre-PR check.
- `.\mvnw.cmd clean verify "-Declipselink.version=3.0.4"` validates the plugin against the EclipseLink 3.x compatibility target.
- `.\mvnw.cmd clean verify "-Declipselink.version=4.0.9"` validates the plugin against the EclipseLink 4.x compatibility target.
- `.\mvnw.cmd test` runs unit tests only; use this only for fast feedback if unit tests are added later.
- `.\mvnw.cmd clean package` creates the plugin JAR in `target/`.
- `.\mvnw.cmd help:describe -Dplugin=io.github.xsavikx:staticweave-maven-plugin -Ddetail` inspects generated plugin metadata.
- `.\mvnw.cmd -Dpublish=github deploy` or `.\mvnw.cmd -Dpublish=central deploy` uses the configured release profiles.

## Coding Style & Naming Conventions
The codebase is Java 8 and uses standard Maven plugin annotations from `org.apache.maven.plugins.annotations`. Follow the existing style: 2-space indentation in Java, braces on the same line, `UpperCamelCase` for classes, `lowerCamelCase` for fields and methods, and package names under `io.github.xsavikx.eclipselink.mojo`. Keep Mojo parameters explicit with `@Parameter` and prefer short, targeted log messages through Maven's logger.

## Testing Guidelines
Integration coverage lives in `src/it/` via `maven-invoker-plugin`; keep those fixtures aligned with the supported EclipseLink matrix and verify that weaving produces woven bytecode, not just a successful build. When adding unit tests, place them in `src/test/java` mirroring the production package layout. Prefer JUnit-based tests for isolated behavior and verify error handling around classpath construction, logging levels, and weaving configuration. Name test classes `*Test` and run them with `.\mvnw.cmd test`. Run `.\mvnw.cmd clean verify` before opening a pull request, and include any version-specific `-Declipselink.version=...` command used for compatibility validation.

## Commit & Pull Request Guidelines
Recent history uses short, imperative commit subjects such as `Update Maven wrapper, workflows, dependencies, and plugins versions` and `Fixate plugin versions`. Keep subjects concise, capitalized, and action-oriented. Pull requests should describe the behavior change, note any dependency or plugin-version updates, reference related issues, and include the exact Maven command used for verification.
