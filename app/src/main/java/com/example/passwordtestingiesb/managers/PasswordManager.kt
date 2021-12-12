package com.example.passwordtestingiesb.managers

import com.example.passwordtestingiesb.R
import com.example.passwordtestingiesb.utils.ValidationsUtils

object PasswordManager {
    fun calculatePassword(password: String): PasswordStatus {

        val invalidTypes = mutableListOf<InvalidType>()

        if (password.length < 8 ) {
            invalidTypes.add(InvalidType.Minimo8Caracteres)
        }

        if (ValidationsUtils.isContainDigit(password).not()) {
            invalidTypes.add(InvalidType.Minimo1Numero)
        }

        if (ValidationsUtils.isContainSpecialCharacter(password).not()) {
            invalidTypes.add(InvalidType.Minimo1CaractereEspecial)
        }

        if (password.length > 15 ) {
            invalidTypes.add(InvalidType.MaiorQue15Caracteres)
        }

        if (invalidTypes.isNotEmpty()) {
            return PasswordStatus.Invalid(invalidTypes)
        }

        return PasswordStatus.Valid(StrengthLevel.STRONG)
    }
}

sealed class PasswordStatus {
    class Invalid(
        val type: List<InvalidType>
    ) : PasswordStatus()

    class Valid(
        val strengthLevel: StrengthLevel
    ) : PasswordStatus()
}

enum class InvalidType(val reason: String) {
    Minimo8Caracteres("No minimo 8 caracteres"),
    Minimo1Numero("Ao menos 1 numero"),
    Minimo1CaractereEspecial("Ao menos 1 caractere especial"),
    MaiorQue15Caracteres("Maior que 15 caracteres")
}

enum class StrengthLevel(val color: Int){
    NONE(R.color.password_strength_clean),
    WEAK(R.color.password_strength_weak),
    MEDIUM(R.color.password_strength_medium),
    STRONG(R.color.password_strength_strong)
}

