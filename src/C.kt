import java.io.*
import java.util.*

fun main() {
    C.main()
}

object C {

    private val stdin = System.`in`
    private val stdout = System.out
    private val scanner = QuickReader(stdin)

    init {
        System.setOut(PrintStream(stdout))
    }

    fun main() {
        val n = scanner.nextInt()
        val m = scanner.nextInt()

        val count = n - m + 1
        val strs = Array<CharArray>(count) {
            scanner.next().toCharArray()
        }

        val s = CharArray(n)

        for (i in 0 until n) {

            val chars = mutableMapOf<Char, Int>()

            for (j in 0 until count) {
                val c = strs[j][i]
                chars.putIfAbsent(c, 0)
                chars[c] = chars[c]!! + 1
            }

            s[i] = chars.toList().maxBy { it.second }!!.first
        }

        println(String(s))
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



