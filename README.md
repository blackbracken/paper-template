# paper-template

## Environment

- [JDK 17](https://openjdk.java.net/projects/jdk/17/)
- [PaperMC 1.20.4](https://papermc.io/downloads/paper)
- [vavr](https://docs.vavr.io/)
- [lombok](https://projectlombok.org/)
- [junit5](https://junit.org/junit5/)

## Setup

```powershell
cp ./pre-commit ./.git/hooks/pre-commit
```

## Commands

|         command         |                       usage                        |
|:-----------------------:|:--------------------------------------------------:|
|   ./build_and_run.ps1   | build and reboot the server for debug (powershell) |
|     ./gradlew test      |                     unit test                      |
| ./gradlew spotlessApply |                      reformat                      |
