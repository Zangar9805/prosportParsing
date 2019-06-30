package kz.zangpro.jsoup

import org.jsoup.Jsoup

class Repository {

    private val url = "https://prosports.kz/kz/football/kpl/table"
    private val url2 = "https://prosports.kz/kz/football/kpl"
    private var name = ""
    private var imgUrl = ""
    private var goals = ""
    private var runk = 0
    private var point = 0
    private var wins = 0
    private var draws = 0
    private var losts = 0
    private var games = 0

    private var title = ""
    private var newsImgUrl = ""
    private var date = ""
    private var views = 0

    fun getTableKPL(): MutableList<TablesModel> {
        val doc = Jsoup.connect(url).get()

        val table = doc.select("div.table.active").select("table").select("tbody")
        val trs = table.select("tr")
        val listTable = mutableListOf<TablesModel>()

        for (i in trs.indices) {
            val tds = trs[i].select("td")
            for (j in tds.indices) {

                when (j) {
                    0 -> runk = tds[j].select("span").text().toInt()
                    1 -> {
                        name = tds[j].select("div.title").select("a").text().split(" ")[0]
                        imgUrl = tds[j].select("div.flag").select("img").attr("src")
                    }
                    2 -> games = tds[j].text().toInt()
                    3 -> wins = tds[j].text().toInt()
                    4 -> draws = tds[j].text().toInt()
                    5 -> losts = tds[j].text().toInt()
                    6 -> goals = tds[j].text()
                    7 -> point = tds[j].text().toInt()
                }
            }
            listTable.add(TablesModel(runk, name, imgUrl, games, wins, draws, losts, goals, point))
        }

        return listTable
    }

    fun getKPLNews(): MutableList<NewsModel>{
        val listNews = mutableListOf<NewsModel>()
        val doc = Jsoup.connect(url2).get()

        val news = doc.select("div.content").select("div.news").select("div.items").select("div.item")

        for (n in news){
            title = n.select("div.row.info")
                .select("div.right").select("div.title")
                .select("a").text()
            newsImgUrl = n.select("div.row.info")
                .select("div.image").select("a")
                .select("img").attr("src")
            date = n.select("div.row.top")
                .select("div.date").text()

            listNews.add(NewsModel(title, newsImgUrl, date, views))
        }

        return listNews
    }
}