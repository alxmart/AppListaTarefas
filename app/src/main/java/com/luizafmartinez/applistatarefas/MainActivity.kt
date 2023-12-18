package com.luizafmartinez.applistatarefas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.luizafmartinez.applistatarefas.adapter.TarefaAdapter
import com.luizafmartinez.applistatarefas.database.TarefaDAO
import com.luizafmartinez.applistatarefas.databinding.ActivityMainBinding
import com.luizafmartinez.applistatarefas.model.Tarefa

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var listaTarefas = emptyList<Tarefa>()

    private var tarefaAdapter: TarefaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fabAdicionar.setOnClickListener {
            val intent = Intent(this, AdicionarTarefaActivity::class.java)
            startActivity(intent)
        }

        // RecyclerView
        tarefaAdapter = TarefaAdapter(

            { id -> confirmarExclusao(id) }
        )


        binding.rvTarefas.adapter = tarefaAdapter

        binding.rvTarefas.layoutManager = LinearLayoutManager(this)

    }

    private fun confirmarExclusao(id: Int) {

        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Confirmar exclusão")
        alertBuilder.setMessage("Deseja realmente excluir a tarefa ?")

        alertBuilder.setPositiveButton("Sim") { _, _ ->

            val tarefaDAO = TarefaDAO(this)

            if ( tarefaDAO.remover( id ) ) {
                atualizarListaTarefas()
                Toast.makeText(
                    this,
                    "Sucesso ao remover tarefa",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Erro ao remover tarefa !",
                    Toast.LENGTH_SHORT).show()
            }
            

        }

        alertBuilder.setNegativeButton("Não") { _, _ ->  }

        alertBuilder.create().show()

    }

    private fun atualizarListaTarefas() {
        val tarefaDao = TarefaDAO(this) // this ou app (application context)
        listaTarefas = tarefaDao.listar()
        tarefaAdapter?.adicionarLista( listaTarefas )
    }


    override fun onStart() {
        super.onStart()
        atualizarListaTarefas()
    }

}