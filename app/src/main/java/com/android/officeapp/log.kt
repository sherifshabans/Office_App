package com.android.kotlinmvvmtodolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class log : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btn1: Button =findViewById(R.id.btn)
        //val btn2: Button =findViewById(R.id.button2)
        btn1.setOnClickListener {

                val     emailent :EditText=findViewById(R.id.ed_email)
                val  passent :EditText =findViewById(R.id.ed_pass)
                //  get value
                val email:String =emailent.text.toString()
                val pass:String =passent.text.toString()
        if((email =="0000")&& (pass=="0000")){
          val intent = Intent(this,SplashScreen::class.java)
          startActivity(intent)

                  }



        /*     val myhome = homeFragment()

                 val fragment: Fragment? =
                     supportFragmentManager.findFragmentByTag(homeFragment::class.java.simpleName)
                 if (fragment !is AddFragment) {
                     supportFragmentManager.beginTransaction()
                         .add(R.id.login ,myhome, homeFragment::class.java.simpleName)
                         .commit()
                 }

                 btn1.visibility= View.GONE
            */
        }



      }
}