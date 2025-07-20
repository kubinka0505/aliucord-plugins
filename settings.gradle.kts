rootProject.name = "AliucordPlugins"

include(
    "Freedom"
)

rootProject.children.forEach {
    it.projectDir = file("plugins/kotlin/${it.name}")
}
