package com.luizafmartinez.applistatarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luizafmartinez.applistatarefas.databinding.ActivityAdicionarTarefaBinding
import com.luizafmartinez.applistatarefas.databinding.ActivityMainBinding

class AdicionarTarefaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdicionarTarefaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}