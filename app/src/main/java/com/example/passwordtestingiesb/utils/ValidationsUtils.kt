package com.example.passwordtestingiesb.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

object ValidationsUtils {

    fun isContainDigit(str: String): Boolean {
        return str.matches(Regex(".*\\d.*"))
    }

    fun isContainSpecialCharacter(str: String): Boolean {
        val p: Pattern = Pattern.compile("[^A-Za-z0-9]", Pattern.CASE_INSENSITIVE)
        val m: Matcher = p.matcher(str)
        return m.find()
    }
}