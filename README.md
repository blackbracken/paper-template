# paper-template

## Environment
- basement
  - [jdk 17](https://openjdk.java.net/projects/jdk/17/)
  - [paper 1.20.4](https://papermc.io/downloads/paper)
- libs
  - [vavr](https://docs.vavr.io/)
  - [lombok](https://projectlombok.org/)
  - [guice](https://github.com/google/guice)
  - [rxjava](https://github.com/ReactiveX/RxJava)
- testing
  - [junit5](https://junit.org/junit5/)
  - [truth](https://truth.dev/)
- formatter
  - [spotless](https://github.com/diffplug/spotless)
  - [prettier](https://prettier.io/)
    - [prettier-java](https://github.com/jhipster/prettier-java)

## Setup

```powershell
cp ./pre-commit ./.git/hooks/pre-commit
winget install -e --id OpenJS.NodeJS
npm i
```

## Commands

|         command         |                         usage                          |
|:-----------------------:|:------------------------------------------------------:|
|   ./build_and_run.ps1   | build and reboot the server for debug (for powershell) |
|     ./gradlew test      |                       unit test                        |
| ./gradlew spotlessApply |                        reformat                        |
