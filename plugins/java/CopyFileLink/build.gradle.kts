version = "1.0.2"
description = "Tap and hold file attachment (such as .apk, .txt and .apk files) in chat to copy their links!"

aliucord {

    changelog.set("""
        1.0.2
        - Use url field instead of proxyUrl

        1.0.1
        - Made toast less verbose by removing the copied URL from it
        - Added Themer-aware toast

        1.0.0
        - First release!
    """.trimIndent())

    excludeFromUpdaterJson.set(false)
}