package kz.zangpro.jsoup

data class TablesModel(
    val clubRunk: Int,
    val clubName: String,
    val clubLogo: String,
    val countGame: Int,
    val countWins: Int,
    val countDraw: Int,
    val countLosts: Int,
    val countGols: String,
    val countPoints: Int)

data class NewsModel(val title: String,
                     val imgUrl: String,
                     val date: String,
                     val views: Int,
                     val site: String = "prosport.kz")