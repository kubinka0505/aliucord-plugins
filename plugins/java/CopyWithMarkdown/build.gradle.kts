version = "1.0.1"
description = "Copy message with markdown! Use 'Separate buttons' setting to use this alongside Discord's default."

aliucord {

    changelog.set("""
        1.0.1
        - Fixed bug where the copy button would not be replaced on non-English devices
        - Added support for 21 different languages, including Spanish, Polish, Turkish and Hindi.

        1.0.0
        - First release!
    """.trimIndent())

    excludeFromUpdaterJson.set(false)
}