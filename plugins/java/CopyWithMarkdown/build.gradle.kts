version = "1.0.3"
description = "Copy message with markdown! Use 'Separate buttons' setting to use this alongside Discord's default."

aliucord {

    changelog.set("""
        1.0.3
        - Added translations for 'Copied to clipboard' and 'Copied with Markdown.'
        - Added Themer-aware toasts

        1.0.2
        - Show toast when tapping the copy buttons

        1.0.1
        - Fixed bug where the copy button would not be replaced on non-English devices
        - Added support for 21 different languages, including Spanish, Polish, Turkish and Hindi.

        1.0.0
        - First release!
    """.trimIndent())

    excludeFromUpdaterJson.set(false)
}