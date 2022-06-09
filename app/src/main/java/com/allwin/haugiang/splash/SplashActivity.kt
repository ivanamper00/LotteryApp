package com.allwin.haugiang.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.allwin.haugiang.R
import com.allwin.haugiang.app.MainActivity
import com.dakulangsakalam.customwebview.presentation.ui.jump.JumpActivity
import com.dakulangsakalam.customwebview.presentation.ui.jump.JumpType

class SplashActivity : JumpActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashAction(JumpType.JUMP_LINK, 1){ _, _ ->
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(MainActivity.getStartIntent(this))
                finish()
            }, 1500)
        }
    }
}