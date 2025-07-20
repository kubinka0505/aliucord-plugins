package com.kubinka.freedom

import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.patcher.after

@AliucordPlugin
class Freedom : Plugin() {
	private val characters = mapOf(
		'a' to 'ą',
		'e' to 'ę',
		'c' to 'ć',
		'l' to 'ł',
		'n' to 'ń',
		's' to 'ś',
		'z' to 'ź'
	)

	private fun replaceCharacters(input: String): String {
		return input.map { char -> characters[char] ?: char }.joinToString("")
	}

	override fun start(ctx: Context) {
		val clazz = Class.forName("com.discord.models.domain.ModelMessageSendRequest")

		patcher.after(clazz, "getContent", *arrayOf()) { call ->
			val original = call.result as String
			val modified = replaceCharacters(original)
			call.setResult(modified)
		}
	}

	override fun stop(ctx: Context) {
		patcher.unpatchAll()
	}
}
