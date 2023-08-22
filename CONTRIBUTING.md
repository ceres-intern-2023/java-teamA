基本的には　
https://github.com/ceres-intern-2023/java-skelton
のREADMEに従えば起動できます。

ただローカルPC（各々のPC）で動作させることを前提にしているので以下のインストールが、各自必要になります。

- Docker
- JDK
- Node

## Docker

### mac

以下よりインストールする
https://docs.docker.com/desktop/install/mac-install/

### windows

Windowsの場合は以下のサイト等を参考にインストールしてください。

https://docs.docker.jp/docker-for-windows/install.html

## JDK

SDKMANというツールで様々なバージョンのJDKをインストール可能なので、まずそれかインストールしてください。

https://sdkman.io/

SDKMANがインストールできたら、以下コマンドでJDKをインストールしていきます。

```
sdk install java 17.0.8-amzn
sdk default java 17.0.8-amzn
```

インストールが終わり、以下コマンド実行してjava17のバージョンが確認できれば成功です。
```
java --version
```

## Node

### mac
以下よりインストールコマンド実行しインストールしてください。
https://github.com/nvm-sh/nvm#installing-and-updating

### windows

以下より`nvm-setup.zip`をダウンロードしインストールしてください。
https://github.com/coreybutler/nvm-windows/releases

### 共通

正しくインストールされていば、以下でnvmバージョンが確認できます。
```
nvm --version
```

nvmを使ってNodeをインストールしていきます。
```
nvm install 18.17.1
nvm use default v18.17.1
```

以下でnodeのバージョンが確認できれば成功です。
```
node -v
```
