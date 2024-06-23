package com.dryingcore

fun main() {
    val task = mutableListOf<String>()
    var running = true

    while(running) {
        println("\n===== TO-DO LIST =====")
        println("1. Adicionar tarefa")
        println("2. Listar tarefas")
        println("3. Marcar tarefa como concluída")
        println("4. Remover tarefa")
        println("5. Sair")
        print("Escolha uma opção: ")

        when(readLine()!!.toInt()) {
            1 -> addTask(task)
            2 -> listTasks(task)
            3 -> markAsCompleted(task)
            4 -> removeTask(task)
            5 -> {
                println("Saindo...")
                running = false
            }
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun addTask(tasks: MutableList<String>) {
    print("Digite uma nova tarefa: ")
    val task = readLine()!!
    tasks.add(task)
    println("Tarefa adicionada!")
}

fun listTasks(tasks: MutableList<String>) {
    if (tasks.isEmpty()) {
        println("Nenhuma tarefa adicionada.")
    } else {
        println("\n===== SUAS TAREFAS =====")
        for ((index, task) in tasks.withIndex()) {
            println("${index + 1}. $task")
        }
    }
}

fun markAsCompleted(tasks: MutableList<String>) {
    if (tasks.isEmpty()) {
        println("nenhuma tarefa para marcar como concluida")
        return
    }

    listTasks(tasks)
    print("Digite o numero da tarefa concluida")
    val taskNumber = readLine()!!.toInt()
    if (taskNumber in 1..tasks.size) {
        tasks[taskNumber - 1] += " (Concluida)"
        println("Tarefa marcada como concluida!")
    } else {
        println("Numero invalido, tente novamente")
    }
}

fun removeTask(tasks: MutableList<String>) {
    if (tasks.isEmpty()) {
        println("Nenhuma tarefa para remover.")
        return
    }

    listTasks(tasks)
    print("Digite o número da tarefa a ser removida: ")
    val taskNumber = readLine()!!.toInt()
    if (taskNumber in 1..tasks.size) {
        tasks.removeAt(taskNumber - 1)
        println("Tarefa removida!")
    } else {
        println("Número inválido, tente novamente.")
    }
}