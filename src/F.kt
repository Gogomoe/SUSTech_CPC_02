import java.io.*
import java.util.*

fun main() {
    F.main()
}

object F {

    private val stdin = System.`in`
    private val stdout = System.out
    private val scanner = QuickReader(stdin)

    init {
        System.setOut(PrintStream(stdout))
    }

    fun main() {
        val t = scanner.nextInt()
        repeat(t) {
            process()
        }
    }

    private fun process() {

        val s10 = scanner.nextInt().toLong()
        val s20 = scanner.nextInt().toLong()
        val s30 = scanner.nextInt().toLong()

        var s1 = s10
        var s2 = s20
        var s3 = s30

        val common = scanner.nextInt().toLong()

        var p1 = -1
        var p2 = -1
        var p3 = -1

        for (i in 1..7100000) {
            s1 = s1 * s1 % 2147483231
            s2 = s2 * s2 % 2147483231
            s3 = s3 * s3 % 2147483231

            if (p1 == -1 && s1 == common) {
                p1 = i
            }
            if (p2 == -1 && s2 == common) {
                p2 = i
            }
            if (p3 == -1 && s3 == common) {
                p3 = i
            }
            if (p1 != -1 && p2 != -1 && p3 != -1) {
                break
            }
        }

        val minP = minOf(p1, p2, p3)

        s1 = s10
        s2 = s20
        s3 = s30

        repeat(p1 - minP) {
            s1 = s1 * s1 % 2147483231
        }
        repeat(p2 - minP) {
            s2 = s2 * s2 % 2147483231
        }
        repeat(p3 - minP) {
            s3 = s3 * s3 % 2147483231
        }

        while (true) {
            if (s1 == s2 && s2 == s3){
                println(s1)
                return
            }
            s1 = s1 * s1 % 2147483231
            s2 = s2 * s2 % 2147483231
            s3 = s3 * s3 % 2147483231
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



