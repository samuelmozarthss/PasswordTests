package com.example.passwordtestingiesb

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.passwordtestingiesb.databinding.FragmentPasswordBinding
import com.example.passwordtestingiesb.managers.InvalidType
import com.example.passwordtestingiesb.managers.PasswordManager
import com.example.passwordtestingiesb.managers.PasswordStatus
import com.example.passwordtestingiesb.managers.StrengthLevel


class PasswordFragment : Fragment() {

    private var _binding: FragmentPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includePassword.tiePassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(password: Editable) {

                val result = PasswordManager.calculatePassword(password.toString())

                binding.includePassword.eightImg.visibility = View.VISIBLE
                binding.includePassword.eightCharacters.visibility = View.VISIBLE
                binding.includePassword.numbersImg.visibility = View.VISIBLE
                binding.includePassword.numbers.visibility = View.VISIBLE
                binding.includePassword.charactersImg.visibility = View.VISIBLE
                binding.includePassword.characters.visibility = View.VISIBLE
                binding.includePassword.fifteenCharactersImg.visibility = View.VISIBLE
                binding.includePassword.fifteenCharacters.visibility = View.VISIBLE

                if (result is PasswordStatus.Invalid) {
                    if (!result.type.contains(InvalidType.Minimo8Caracteres)) {
                        binding.includePassword.eightImg.visibility = View.GONE
                        binding.includePassword.eightCharacters.visibility = View.GONE
                    }

                    if (!result.type.contains(InvalidType.Minimo1Numero)) {
                        binding.includePassword.numbersImg.visibility = View.GONE
                        binding.includePassword.numbers.visibility = View.GONE
                    }

                    if (!result.type.contains(InvalidType.Minimo1CaractereEspecial)) {
                        binding.includePassword.charactersImg.visibility = View.GONE
                        binding.includePassword.characters.visibility = View.GONE
                    }

                    if (!result.type.contains(InvalidType.MaiorQue15Caracteres)) {
                        binding.includePassword.fifteenCharactersImg.visibility = View.GONE
                        binding.includePassword.fifteenCharacters.visibility = View.GONE
                    }
                } else {
                    binding.includePassword.eightImg.visibility = View.GONE
                    binding.includePassword.eightCharacters.visibility = View.GONE
                    binding.includePassword.numbersImg.visibility = View.GONE
                    binding.includePassword.numbers.visibility = View.GONE
                    binding.includePassword.charactersImg.visibility = View.GONE
                    binding.includePassword.characters.visibility = View.GONE
                    binding.includePassword.fifteenCharactersImg.visibility = View.GONE
                    binding.includePassword.fifteenCharacters.visibility = View.GONE
                }

                if (password.toString().isEmpty()) {
                    binding.includePassword.strengthLevelTxt.text = ""
                    binding.includePassword.strengthLevelIndicatorWeak.setBackgroundResource(
                        StrengthLevel.NONE.color)
                    binding.includePassword.strengthLevelIndicatorMedium.setBackgroundResource(
                        StrengthLevel.NONE.color)
                    binding.includePassword.strengthLevelIndicatorStrong.setBackgroundResource(
                        StrengthLevel.NONE.color)
                } else if (password.toString().length < 8) {
                    binding.includePassword.strengthLevelTxt.text = "Fraca"
                    binding.includePassword.strengthLevelIndicatorWeak.setBackgroundResource(
                        StrengthLevel.WEAK.color)
                    binding.includePassword.strengthLevelIndicatorMedium.setBackgroundResource(
                        StrengthLevel.NONE.color)
                }
                if (password.toString().length >= 8) {
                    binding.includePassword.strengthLevelTxt.text = "Média"
                    binding.includePassword.strengthLevelIndicatorWeak.setBackgroundResource(
                        StrengthLevel.MEDIUM.color)
                    binding.includePassword.strengthLevelIndicatorMedium.setBackgroundResource(
                        StrengthLevel.MEDIUM.color)
                    binding.includePassword.strengthLevelIndicatorStrong.setBackgroundResource(
                        StrengthLevel.NONE.color)
                }
                if (password.toString().length >= 10) {
                    binding.includePassword.strengthLevelTxt.text = "Forte"
                    binding.includePassword.strengthLevelIndicatorWeak.setBackgroundResource(
                        StrengthLevel.STRONG.color)
                    binding.includePassword.strengthLevelIndicatorMedium.setBackgroundResource(
                        StrengthLevel.STRONG.color)
                    binding.includePassword.strengthLevelIndicatorStrong.setBackgroundResource(
                        StrengthLevel.STRONG.color)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}