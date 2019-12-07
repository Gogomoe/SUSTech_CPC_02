import java.io.*
import java.util.*

fun main() {
    H2.main()
}

object H2 {

    private val stdin = System.`in`
    private val stdout = System.out
    private val scanner = QuickReader(stdin)

    init {
        System.setOut(PrintStream(stdout))
    }

    private var origin: Int = 0
    private var income: Int = 0
    private var games: Int = 0
    private var decrease: Int = 0
    private lateinit var cost: LongArray


    fun main() {
        origin = scanner.nextInt()
        income = scanner.nextInt()
        games = scanner.nextInt()
        decrease = scanner.nextInt()

        cost = LongArray(games)
        for (i in 0 until games) {
            cost[i] = scanner.nextLong()
        }

        var l: Long = 1L
        var r: Long = 100000000000L

        while (l < r) {
            val mid = (l + r) / 2
            if (check(mid)) {
                r = mid
            } else {
                l = mid + 1
            }
        }

        var money = origin.toLong() + income * (r - 1)
        val decreaseCount = decrease * (r - 1)
        var sum = 0L
        for (c in cost) {
            if (c > decreaseCount) {
                sum += c - decreaseCount
            } else {
                sum += ((c - 1) % decrease) + 1
            }
        }

        if (money >= sum) {
            println("$r morning")
        } else {
            println("$r evening")
        }

    }

    fun check(it: Long): Boolean {
        var money = origin.toLong() + income * (it - 1)

        val decreaseCount = decrease * (it - 1)

        var sum = 0L
        for (c in cost) {
            if (c > decreaseCount) {
                sum += c - decreaseCount
            } else {
                sum += ((c - 1) % decrease) + 1
            }
        }

        money += income

        return money >= sum;
    }

    private class QuickReader(stream: InputStream) {
        var reader: BufferedReader = BufferedReader(InputStreamReader(stream), 32768)
        var tokenizer: StringTokenizer?

        operator fun next(): String {
            while (tokenizer == null || !tokenizer!!.hasMoreTokens()) {
                tokenizer = try {
                    StringTokenizer(reader.readLine())
                } catch (e: IOException) {
                    throw RuntimeException(e)
                }
            }
            return tokenizer!!.nextToken()
        }

        fun nextInt(): Int {
            return next().toInt()
        }

        fun nextLong(): Long {
            return next().toLong()
        }

        fun nextDouble(): Double {
            return next().toDouble()
        }

        init {
            tokenizer = null
        }
    }
}



