package com.android.kotlinmvvmtodolist

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.android.kotlinmvvmtodolist.ui.MainActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val anim = findViewById<LottieAnimationView>(R.id.animationView)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
//           anim.visibility=View.VISIBLE
           anim.playAnimation()
        },2000)
        anim.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {
                val intent= Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)

            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
                val intent= Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)

            }


        })
        /*
        val timer = object  : CountDownTimer(3000,1000){
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {

//                val intent= Intent(this@SplashScreen,LoginScreen::class.java)

                val intent= Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)


            }
        }
        timer.start()
*/
    }
}