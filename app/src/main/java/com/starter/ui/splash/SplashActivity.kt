package com.starter.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.starter.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val op =
      Completable
        .complete()
        .delay(1000, TimeUnit.MILLISECONDS)
        .subscribe {
          val intent = Intent(this, MainActivity::class.java)
          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
          startActivity(intent)
        }
    disposables.add(op)
  }

  override fun onDestroy() {
    super.onDestroy()
    disposables.clear()
  }

}