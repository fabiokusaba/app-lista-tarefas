package com.fabiokusaba.applistatarefas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fabiokusaba.applistatarefas.adapter.TarefaAdapter
import com.fabiokusaba.applistatarefas.database.TarefaDAO
import com.fabiokusaba.applistatarefas.databinding.ActivityMainBinding
import com.fabiokusaba.applistatarefas.model.Tarefa

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

        //Recyclerview
        tarefaAdapter = TarefaAdapter()
        binding.rvTarefas.adapter = tarefaAdapter

        binding.rvTarefas.layoutManager = LinearLayoutManager(this)
    }

    private fun atualizarListaTarefas() {
        val tarefaDAO = TarefaDAO(this)
        listaTarefas = tarefaDAO.listar()
        tarefaAdapter?.adicionarLista(listaTarefas)
    }

    override fun onStart() {
        super.onStart()
        atualizarListaTarefas()
    }
}