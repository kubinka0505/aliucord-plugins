package com.kubinka.freedom

import android.content.Context
import com.discord.models.domain.ModelMessageSendRequest
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.patcher.after

@AliucordPlugin
class main : Plugin() {
    private val homoglyphs = mapOf(
        'a' to 'а', 'e' to 'е', 'o' to 'о', 'p' to 'р', 'c' to 'с', 'y' to 'у', 'x' to 'х',
        'A' to 'А', 'E' to 'Е', 'O' to 'О', 'P' to 'Р', 'C' to 'С', 'X' to 'Х'
    )

    private fun replaceHomoglyphs(input: String): String {
        return input.map { char -> homoglyphs[char] ?: char }.joinToString("")
    }

    override fun start(ctx: Context) {
        patcher.after<ModelMessageSendRequest>("getContent") { call ->
            val original = call.result as String
            val modified = replaceHomoglyphs(original)
            call.setResult(modified)
        }
    }

    override fun stop(ctx: Context) {
        patcher.unpatchAll()
    }
}
