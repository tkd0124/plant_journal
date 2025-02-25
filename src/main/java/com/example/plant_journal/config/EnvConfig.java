package com.example.plant_journal.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMalformed()  // もし .env に構文エラーがあっても無視
            .ignoreIfMissing()     // .env がなくてもエラーにしない
            .load();

    public static String getEnv(String key) {
        return dotenv.get(key, ""); // デフォルト値を "" にして NullPointerException を防ぐ
    }
}
