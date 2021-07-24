package com.example.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val edt_email = findViewById<EditText>(R.id.edt_email)
        val edt_pass = findViewById<EditText>(R.id.edt_pass)
        val tv_email = findViewById<TextView>(R.id.tv_email)
        val tv_pass = findViewById<TextView>(R.id.tv_pass)
        val btn_login = findViewById<Button>(R.id.btn_submit)

        btn_login.setOnClickListener() {
            val insertedEmailText: String = edt_email.text.toString()
            val insertedPasswordText: String = edt_pass.text.toString()
            tv_email.text = insertedEmailText
            tv_pass.text = insertedPasswordText
            val sharedPreference: SharedPreferences =
                getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor? = sharedPreference.edit()
            editor?.apply {
                putString("email", insertedEmailText)
                putString("password", insertedPasswordText)
            }?.apply()
            if (insertedEmailText.isEmpty() || insertedPasswordText.isEmpty()) {
                Toast.makeText(this, "Something Missing", Toast.LENGTH_SHORT).show()
            } else {
                tv_email.text = insertedEmailText
                tv_pass.text = insertedPasswordText
                Toast.makeText(this, "Saved Data", Toast.LENGTH_SHORT).show()
            }

        }
        val sharedPreference: SharedPreferences =
            getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        val savedEmailString: String? = sharedPreference.getString("email", null)
        val savedPasswordString: String? = sharedPreference.getString("password", null)

        tv_email.text = savedEmailString
        tv_pass.text = savedPasswordString
    }
}