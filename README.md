# paper-template

## Usage

### Build and Run (Windows)
```bash
./build_and_run.ps1
```
IntelliJ IDEAで利用するにはConfigurationsにShell Scriptとして上記のps1ファイルを指定して追加してください。
また、Interpreterには `powershell.exe` を指定してください。

### Unit test
```bash
./gradlew test
```

## Environment
- [JDK 17](https://openjdk.java.net/projects/jdk/17/)
- [PaperMC 1.20.4](https://papermc.io/downloads/paper)


### Used libraries
- [vavr](https://docs.vavr.io/)
- [lombok](https://projectlombok.org/)
- [junit5](https://junit.org/junit5/)
