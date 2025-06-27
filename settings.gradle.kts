rootProject.name = "AliucordPlugins"

include(
    "CopyWithMarkdown",
    "CopyFileLink"
)

rootProject.children.forEach {

    it.projectDir = file("plugins/java/${it.name}")
}