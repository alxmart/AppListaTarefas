package com.luizafmartinez.applistatarefas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.luizafmartinez.applistatarefas.database.TarefaDAO
import com.luizafmartinez.applistatarefas.databinding.ActivityMainBinding
import com.luizafmartinez.applistatarefas.model.Tarefa

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var listaTarefas = emptyList<Tarefa>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fabAdicionar.setOnClickListener {
            val intent = Intent(this, AdicionarTarefaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        val tarefaDao = TarefaDAO(this) // this ou app (application context)
        listaTarefas = tarefaDao.listar()

        listaTarefas.forEach { tarefa ->
            Log.i("info_db", "${tarefa.descricao} \n")
        }
    }

}