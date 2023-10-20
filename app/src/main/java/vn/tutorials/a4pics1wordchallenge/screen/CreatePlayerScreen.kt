package vn.tutorials.a4pics1wordchallenge.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import vn.tutorials.a4pics1wordchallenge.R

class CreatePlayerScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_player_dialog)

        val btnStart: Button = findViewById(R.id.btn_start)
        val etName: EditText = findViewById(R.id.et_name)
        btnStart.setOnClickListener{

            if(etName.text.isEmpty()){
                Toast.makeText(this, "Please enter your name",Toast.LENGTH_LONG).show()
            } else{
                val intent = Intent(this, MainScreen::class.java)
                startActivity(intent)

            }
        }

    }
}
