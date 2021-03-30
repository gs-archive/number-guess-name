package com.gulsah.sayitahminioyunu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.core.view.isVisible
import com.gulsah.sayitahminioyunu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var score = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val randomNumber = (1..100).random()

        binding.editTextTahmin.setOnFocusChangeListener { v, hasFocus ->  binding.textSkor.text = "Skorunuz ${score}"}


        binding.buttonTahmin.setOnClickListener {
            val number = binding.editTextTahmin.text.toString()

            if (validateInput()) {

                checkNumber(randomNumber, number.toInt())
            } else {
                validate()
            }

            if(score == 0){
                binding.textKontrol.text = ""
                gameFinished()
            }

            binding.editTextTahmin.text?.clear()

        }

    }
    private fun validate(){
        if (!validateInput()) return
    }

    private fun validateInput() : Boolean{
        val value = binding.editTextTahmin.text.toString()

        if(value.isEmpty()){
            binding.TextInputLayout.error = getString(R.string.empty_error)
            return false
        }
        if(value.toInt() > 100){
            binding.TextInputLayout.error = getString(R.string.number_error)
            return false
        }
        binding.TextInputLayout.error = null
        return true
    }

    private fun checkNumber(randomNumber :Int , guess : Int){
        if (randomNumber == guess) {
            binding.textKontrol.text = "Tebrikler!!"
            gameFinished()
        }
        else if (randomNumber > guess) {
            binding.textKontrol.text = "daha büyük bir sayı dene"
            score--
        }
        else if (guess > randomNumber) {
            binding.textKontrol.text = "daha küçük bir sayı dene"
            score--
        }
        binding.textSkor.text = "Skorunuz ${score}"
    }

    private fun gameFinished(){
        binding.TextInputLayout.isEnabled = false
        binding.textSkor.isAllCaps = true
        binding.buttonTahmin.text = "Oyunu Bitir"
        binding.buttonTahmin.setOnClickListener {
            finish()
            overridePendingTransition(0, 0)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

    }


}