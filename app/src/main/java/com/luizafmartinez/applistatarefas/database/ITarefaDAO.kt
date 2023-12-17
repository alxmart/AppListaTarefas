package com.luizafmartinez.applistatarefas.database

import com.luizafmartinez.applistatarefas.model.Tarefa

interface ITarefaDAO {
    fun salvar( tarefa: Tarefa): Boolean
    fun atualizar( tarefa: Tarefa): Boolean
    fun remover( idTarefa: Int): Boolean
    fun listar( ): List<Tarefa>
}