package com.fabiokusaba.applistatarefas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fabiokusaba.applistatarefas.database.TarefaDAO
import com.fabiokusaba.applistatarefas.databinding.ActivityMainBinding
import com.fabiokusaba.applistatarefas.model.Tarefa

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

        val tarefaDAO = TarefaDAO(this)
        listaTarefas = tarefaDAO.listar()

        listaTarefas.forEach { tarefa ->
            Log.i("info_db", "${tarefa.descricao}\n")
        }
    }
}