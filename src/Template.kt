import java.io.*
import java.util.*

fun main() {
    Template.main()
}

object Template {

    private val stdin = System.`in`
    private val stdout = System.out
    private val scanner = QuickReader(stdin)

    init {
        System.setOut(PrintStream(stdout))
    }

    fun main() {

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



