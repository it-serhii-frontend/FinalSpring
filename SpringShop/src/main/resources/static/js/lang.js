function settingsLang(lang) {
    document.cookie = "lang=" + lang + "; path=/; max-age=" + (365 * 24 * 60 * 60);
    location.reload();
}