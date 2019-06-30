package kz.zangpro.jsoup

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainClass {

    val repo = Repository()

    fun getTable(){
        runBlocking {
            repo.getTableKPL().forEach{
                println(it)
            }
        }
    }

    fun getNews(){
        runBlocking {
            repo.getKPLNews().forEach{
                println(it)
            }
        }
    }
}