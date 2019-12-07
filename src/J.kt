import java.io.*
import java.util.*
import kotlin.math.max

/**
 * 没过
 */
fun main() {
    J.main()
}

object J {

    private val stdin = System.`in`
    private val stdout = System.out
    private val scanner = QuickReader(stdin)

    private var n: Int = 0
    private lateinit var d: Array<LongArray>
    private lateinit var longest: LongArray
    private lateinit var adj: Array<LinkedList<Pair<Int, Int>>>

    init {
        System.setOut(PrintStream(stdout))
    }

    fun main() {
        n = scanner.nextInt()
        d = Array(n) { LongArray(n) }
        longest = LongArray(n)
        adj = Array(n) { LinkedList<Pair<Int, Int>>() }

        repeat(n - 1) {
            val u = scanner.nextInt() - 1
            val v = scanner.nextInt() - 1
            val w = scanner.nextInt()

            adj[u].add(v to w)
            adj[v].add(u to w)
        }

        calcDistance()

        val q = scanner.nextInt()
        repeat(q) {
            val from = scanner.nextInt() - 1
            val k = scanner.nextInt()

            println(dpLongest(from).rem(k))
        }
    }

    private fun calcDistance() {

    }

    fun dpLongest(from: Int): Long {
        if (longest[from] != 0L) {
            return longest[from]
        }
        var distance = 0L
        for (i in 0 until n) {
            distance = max(distance, d[from][i])
        }
        longest[from] = distance
        return distance
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



