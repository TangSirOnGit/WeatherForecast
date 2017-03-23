package com.tv.forecast.utils

import android.content.Context
import com.tv.forecast.R
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class FileUtils {
    companion object{
        val dataBaseName = "city_code.db"
    }
    fun importDatabase(context: Context) {
        // 存放数据库的目录
        val dirPath = "/data/data/com.tv.forecast/databases"
        val dir = File(dirPath)
        if (!dir.exists()) {
            dir.mkdir()
        }
        // 数据库文件
        val file = File(dir, dataBaseName)
        try {
            if (!file.exists()) {
                file.createNewFile()
                // 加载需要导入的数据库
                val inputStream= context.resources
                        .openRawResource(R.raw.city_code)
                val outputStream = FileOutputStream(file)
                val buffer = ByteArray(inputStream.available())
                inputStream.read(buffer)
                outputStream.write(buffer)
                inputStream.close()
                outputStream.close()
            }

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
