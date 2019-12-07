import java.io.*
import java.util.*
import kotlin.math.ceil

/**
 * 没过
 */
fun main() {
    H.main()
}

object H {

    private val stdin = System.`in`
    private val stdout = System.out
    private val scanner = QuickReader(stdin)

    init {
        System.setOut(PrintStream(stdout))
    }

    fun main() {
        val origin = scanner.nextInt()
        val income = scanner.nextInt()
        val games = scanner.nextInt()
        val decrease = scanner.nextInt()

        val cost = LongArray(games)
        for (i in 0 until games) {
            cost[i] = scanner.nextLong()
        }
        cost.sort()

        var allCost = cost.sum()
        var day = 1L
        var start = 0
        var money = origin.toLong()
        var decreaseCount = decrease.toLong()

        if (decrease == 0) {
            if (money >= allCost) {
                println("1 morning")
            } else {
                val need = ceil((allCost - money).toDouble() / income).toLong()
                println("$need evening")
            }
            return
        }

        while (true) {

            if (money >= allCost) {
                println("$day morning")
                return
            }

            if (games == start) {
                val need = ceil((allCost - money).toDouble() / income).toLong()
                println("${day + need - 1} evening")
                return
            }

            money += income

            if (money >= allCost) {
                println("$day evening")
                return
            }

            while (start < games && cost[start] <= decreaseCount) {
                start++
            }
            allCost -= (games - start) * decrease

            decreaseCount += decrease
            day++
        }

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



