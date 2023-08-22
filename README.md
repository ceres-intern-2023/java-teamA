# springboot3 nuxt3 sample project

# 環境
- Backend
  - Java OpenJDK Corretto-17.0.8
  - SpringBoot 3.1.2
- Frontend
  - Node v18.17.1
  - Nuxt 3.6.5
- Middleware
  - MySQL 8.1.0 
  - Redis 6.2.13
- その他
  - Adminer 4.8.1
 
# 開発時のシステム構成図

![system.png](docs%2Fimg%2Fsystem.png)


Spring Boot3とNuxt.js3を用いたSPAによるサンプルアプリケーション。
本アプリケーションでは以下の画面・機能がある。

# 機能 
- ログイン時
  - ログイン
  - ユーザー一覧表示
- 非ログイン時
  - ログアウト
  - ログインユーザー取得
  - ユーザー登録
  - ユーザー更新（自分）
  - ユーザー削除（自分）

# アプリケーション起動

## Spring Boot起動と共にDockerコンテナも起動する（MySQL/Redisのコンテナ）
```
./gradlew
./gradlew bootRun
```

※`./gradlew`は初回 or プラグイン追加時だけ実行


backend: http://localhost:8080

## Nuxt起動

```
cd client/nuxt
npm install
npm run dev
```
※`npm install`は初回 or プラグイン追加時だけ実行

frontend: http://localhost:3000

## 画面
- TOP
  - ユーザーの一覧表示
  - ![top.png](docs%2Fimg%2Ftop.png)
- Login
  - ユーザーログイン
  - ![login.png](docs%2Fimg%2Flogin.png)
- MyPage
  - ログアウト、ユーザー追加、更新(自分)、削除(自分)
  - ![mypage.png](docs%2Fimg%2Fmypage.png)

# DB操作

## DB Client
dockerでadminerを起動している。

adminerはDB操作ツール

http://localhost:8000
にアクセスして、以下情報でDB接続する

- データベース種類: MySQL
- サーバ: mysql
- ユーザ名: myuser
- パスワード: secret
- データベース: mydatabase



## DBクリーンアップ
テーブル、データの削除を行う。

プロジェクト直下で以下実行
```
./gradlew flywayClean
```

## データ作成
テーブル、データの作成を行う。
userテーブルの作成と、サンプルデータが投入される。

プロジェクト直下で以下実行
```
./gradlew flywayMigrate
```

# サンプルプロジェクト動作確認TODO
cloneしたサンプルプロジェクトが正しく動作するかの確認TODO。
Java、Node、Docker等インストールされていない場合は、CONTRIBUTING.mdを参照ください。

1. [ ] `./gradlew`でspring起動に必要なプラグインインストール
2. [ ] springを起動させ、localhost:8080にアクセスできること
3. [ ] nuxtを起動させ、localhost:3000にアクセスできること
4. [ ] localhost:3000のログインページにアクセスし、ログインできること
5. [ ] マイページでログアウトできること
6. [ ] マイページでユーザー追加ができること
7. [ ] マイページでユーザー更新(自分)ができること
8. [ ] マイページでユーザー削除(自分)ができること
9. [ ] 追加したユーザーでログインできること
