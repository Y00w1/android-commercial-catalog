package com.example.commercialcatalog.repository

import com.example.commercialcatalog.model.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TaskRepository {
    private val db = FirebaseFirestore.getInstance()
    private val tasksRef = db.collection("tasks")

    suspend fun getTasks(): List<Task> =
        tasksRef.get().await().documents.mapNotNull { doc ->
            doc.toObject(Task::class.java)?.let { task ->
                Task(id = doc.id, title = task.title, completed = task.completed)
            }
        }
    suspend fun addTask(task: Task) {
        tasksRef.add(task).await()
    }

    suspend fun updateTask(task: Task) {
        tasksRef.document(task.id).set(task).await()
    }

    suspend fun deleteTask(taskId: String) {
        tasksRef.document(taskId).delete().await()
    }
}