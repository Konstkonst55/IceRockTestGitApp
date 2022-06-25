package com.example.icerocktestgitapp.utils

object Constants {
    const val TOKEN_KEY = "token_key"
    const val USER_KEY = "user_key"
    const val PREFS_NAME_KEY = "prefs_user_data"

    const val REPOSITORIES_COUNT = 10
    const val UNAUTHORIZED_CODE = 401

    const val LANG_COLORS_FILE_DIR = "language_colors.json"
    const val BASE_README_URL = "https://raw.githubusercontent.com/"
    const val README_FILE_NAME = "README.md"
    const val BASE_URL = "https://api.github.com"
    const val MEDIA_TYPE = "application/json"

    const val HEADER_ACCEPT_VALUE = "application/vnd.github.v3+json"
    const val HEADER_ACCEPT_NAME = "Accept"

    const val REPO_SERIAL_LINK = "html_url"
    const val REPO_SERIAL_FORKS = "forks_count"
    const val REPO_SERIAL_STARS = "stargazers_count"
    const val REPO_SERIAL_WATCHERS = "watchers_count"
    const val REPO_SERIAL_BRANCH = "default_branch"
    const val REPO_SERIAL_OWNER_NAME = "login"

    const val TOKEN_VALID_PATTERN = "^[a-zA-Z0-9_-]{0,45}\$"

    const val VALIDATION_VALID = "Valid input"
    const val VALIDATION_INVALID = "Invalid input"
    const val VALIDATION_EMPTY = "Empty input"

    const val ENTERED_KEY = "IS_ENTERED_KEY"

    const val CONNECTION_ERROR = "No internet connection"
    const val README_CONNECTION_ERROR = "No internet connection to get README"
}