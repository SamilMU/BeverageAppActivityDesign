package com.example.h4v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.h4v2.databinding.ActivityMainBinding
import com.example.h4v2.sharedObj.SharedObj

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            auth(binding.etTel.text.toString(),binding.etPass.text.toString())
        }
        binding.btnGuest.setOnClickListener {
            Toast.makeText(this,"Misafir olarak giriş yapıldı.",Toast.LENGTH_SHORT).show()
            nav2MenuActivity()
        }

    }

    // Authorization
    fun auth(username: String, pass: String){
        if(username.equals("01111111111") && pass.equals("#e&it1m")){
            Toast.makeText(this,"Hoşgeldiniz.",Toast.LENGTH_SHORT).show()
            SharedObj.loggedIn = true
            nav2MenuActivity()
        }
        else{
            Toast.makeText(this,"Kullanıcı adı veya parola yanlış.", Toast.LENGTH_SHORT).show()
            SharedObj.loggedIn = false
            binding.etTel.setText("")
            binding.etPass.setText("")
        }
    }

    // Navigate to MenuActivity
    fun nav2MenuActivity(){
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
}