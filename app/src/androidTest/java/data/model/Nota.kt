package net.ivanvega.misnotasb.data.model

import androidx.room.Database
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Nota(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val titulo: String,
    val descripcion: String,
    val fechaCumplimiento: String?
)