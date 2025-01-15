package com.uika.gugusanpenjaminanmutu.data

import com.uika.gugusanpenjaminanmutu.model.Dosen
import com.uika.gugusanpenjaminanmutu.model.DosenData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GugusanRepository {
    private val listDosen = mutableListOf<Dosen>()

    fun getListDosen(): Flow<List<Dosen>> {
        return flowOf(listDosen)
    }

    init {
        if (listDosen.isEmpty()) {
            DosenData.lecturers.forEach { dosen ->
                listDosen.add(
                    Dosen(dosen.id, dosen.name, dosen.photo, dosen.position, dosen.jobdesk)
                )
            }
        }
    }

    companion object {
        @Volatile
        private var instance: GugusanRepository? = null

        fun getInstance(): GugusanRepository =
            instance ?: synchronized(this) {
                GugusanRepository().apply {
                    instance = this
                }
            }
    }

}