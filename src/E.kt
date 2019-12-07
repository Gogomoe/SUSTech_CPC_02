import java.io.*
import java.lang.Exception
import java.lang.Integer.max
import java.lang.StringBuilder
import java.util.*

fun main() {
    E.main()
}

object E {

    private val stdin = System.`in`
    private val stdout = System.out
    private val scanner = Scanner(stdin)

    init {
        System.setOut(PrintStream(stdout))
    }

    val authorFirstName = Array<String?>(21) { null }
    val authorFamilyName = Array<String?>(21) { null }
    var authorSize = 0

    var city: String? = null
    var province: String? = null
    var country: String? = null
    var publisher: String? = null

    var title: String? = null

    var type: String? = null
    var bookName: String? = null
    var bookAuthor: String? = null
    var pageNumberArray: Array<Int>? = null
    var conferenceName: String? = null
    var conferencePlace: String? = null
    var websiteName: String? = null
    var webHttpLink: String? = null
    var date: Int? = null
    var month: Int? = null
    var year: Int? = null

    fun main() {
        val n = scanner.nextInt()
        scanner.nextLine()

        repeat(n) {
            val line = scanner.nextLine()
            val spilt = line.indexOf(":")
            val key = line.substring(0, spilt).trim().unquote()
            val value = line.substring(spilt + 1).trim().unquote()

            if (key.startsWith("Author")) {
                if (key.endsWith("FirstName")) {
                    val num = key.substring(6, key.length - 9).toInt()
                    authorSize = max(authorSize, num)

                    val name = value.split(" ").joinToString(" ") { it.capitalize() }
                    authorFirstName[num] = name
                } else {
                    val num = key.substring(6, key.length - 10).toInt()
                    authorSize = max(authorSize, num)

                    val name = value.split(" ").joinToString(" ") { it.capitalize() }
                    authorFamilyName[num] = name
                }


            } else {
                when (key) {
                    "City" -> city = value
                    "Province" -> province = value
                    "Country" -> country = value
                    "Publisher" -> publisher = value
                    "Title" -> title = value
                    "Type" -> type = value
                    "BookName" -> bookName = value
                    "BookAuthor" -> bookAuthor = value
                    "PageNumberArray" -> {
                        val array = value.unquote()
                            pageNumberArray = array.split(",").map { it.trim().toInt() }.toTypedArray().sortedArray()
                    }
                    "ConferenceName" -> conferenceName = value
                    "ConferencePlace" -> conferencePlace = value
                    "WebsiteName" -> websiteName = value
                    "WebHttpLink" -> webHttpLink = value
                    "Date" -> date = value.toInt()
                    "Month" -> month = value.toInt()
                    "Year" -> year = value.toInt()
                    else -> throw RuntimeException("input error")
                }
            }
        }

        println(
            "@ ${author().show()}; ${publisher().show()}; ${title().show()}; ${citation().show()}; ${time().show()}."
        )
    }

    private fun String.unquote() = this.substring(1, this.length - 1)

    fun String?.show(): String {
        return this ?: ""
    }

    private fun join(l: String?, r: String?, spilt: String): String? {
        return when {
            l == null && r == null -> null
            l == null -> r
            r == null -> l
            else -> "$l$spilt$r"
        }
    }

    private fun author(): String? {
        return when (authorSize) {
            0 -> null
            1 -> makeAuth(1)
            2 -> "${makeAuth(1)} and ${makeAuth(2)}"
            3 -> "${makeAuth(1)}, ${makeAuth(2)} and ${makeAuth(3)}"
            4 -> "${makeAuth(1)}, ${makeAuth(2)}, ${makeAuth(3)} and ${makeAuth(4)}"
            5 -> "${makeAuth(1)}, ${makeAuth(2)}, ${makeAuth(3)}, ${makeAuth(4)} and ${makeAuth(5)}"
            else -> "${makeAuth(1)} et al."
        }
    }

    private fun makeAuth(i: Int): String? {
        val first = authorFirstName[i]
        val family = authorFamilyName[i]

        val initials = makeAuthInitials(first)
        return join(initials, family, " ")
    }


    private fun makeAuthInitials(first: String?): String? {
        if (first == null) {
            return null
        }
        return first.split(" ").filter { it.isNotBlank() }.joinToString(" ") { it[0] + "." }
    }

    private fun publisher(): String? {
        val cp = join(city, province, ", ")
        val cpc = join(cp, country, ", ")
        return join(cpc, publisher, ": ")
    }

    private fun title(): String? {
        return if (title == null) null else "\"${title}\""
    }

    private fun citation(): String? {
        if (type == null) {
            return null
        }
        return when (type) {
            "BOOK" -> citationBook()
            "CONFERENCE" -> citationConference()
            "ONLINE" -> citationOnline()
            else -> throw RuntimeException("error in type")
        }
    }

    private fun citationBook(): String? {
        val b = if (bookName == null) {
            null
        } else {
            val pages = pages()
            val right = if (pages == null) {
                null
            } else {
                ", $pages"
            }
            "From $bookName${right.show()}"
        }
        return if (b == null) {
            null
        } else {
            val a = if (bookAuthor == null) {
                null
            } else {
                " written by $bookAuthor"
            }
            "$b${a.show()}"
        }
    }

    private fun pages(): String? {
        return if (pageNumberArray.isNullOrEmpty()) {
            null
        } else {
            val pageNumber = StringBuilder()
            val joinPages = joinPages()
            joinPages.forEachIndexed { index, s ->
                if (index == 0) {
                    pageNumber.append(s)
                } else if (index == joinPages.size - 1) {
                    pageNumber.append(" and $s")
                } else {
                    pageNumber.append(", $s")
                }
            }

            "page $pageNumber"
        }
    }

    private fun joinPages(): List<String> {
        val list = mutableListOf<String>()

        var start = pageNumberArray!![0]
        var end = pageNumberArray!![0]

        for (i in 1 until pageNumberArray!!.size) {
            val page = pageNumberArray!![i]

            if (page == end + 1) {
                end = page
            } else {
                if (start == end) {
                    list.add("$start")
                } else {
                    list.add("$start-$end")
                }
                start = page
                end = page
            }
        }

        if (start == end) {
            list.add("$start")
        } else {
            list.add("$start-$end")
        }

        return list
    }

    private fun citationConference(): String? {
        if (conferenceName == null) {
            return null
        }

        val p = if (conferencePlace == null) {
            null
        } else {
            " held in $conferencePlace"
        }

        return "In $conferenceName${p.show()}"

    }

    private fun citationOnline(): String? {
        if (websiteName == null) {
            return null
        }

        val l = if (webHttpLink == null) {
            null
        } else {
            ", link:$webHttpLink"
        }

        return "Accessed online from $websiteName${l.show()}"
    }


    private fun time(): String? {
        val dm = if (month == null) {
            null
        } else {
            val d = if (date == null) {
                null
            } else {
                "$date "
            }
            val m = when (month) {
                1 -> "Jan."
                2 -> "Feb."
                3 -> "Mar."
                4 -> "Apr."
                5 -> "May"
                6 -> "Jun."
                7 -> "Jul."
                8 -> "Aug."
                9 -> "Sep."
                10 -> "Oct."
                11 -> "Nov."
                12 -> "Dec."
                else -> throw RuntimeException("month error")
            }
            "${d.show()}$m"
        }
        return join(dm, year?.toString(), " ")
    }

}



