rootProject.name = "AliucordPlugins"

include(
    "CopyWithMarkdown"
)

rootProject.children.forEach {

    it.projectDir = file("plugins/java/${it.name}")
}