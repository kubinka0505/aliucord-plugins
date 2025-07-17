rootProject.name = "AliucordPlugins"

include(
    "CopyWithMarkdown",
    "CopyFileLink",
    "MoreProfile"
)

rootProject.children.forEach {

    it.projectDir = file("plugins/java/${it.name}")
}