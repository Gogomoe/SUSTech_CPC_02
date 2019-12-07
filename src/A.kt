import java.io.*
import java.util.*

private val stdin = System.`in`
private val stdout = System.out
private val scanner = QuickReader(stdin)

lateinit var str: CharArray
lateinit var res: CharArray
lateinit var used: BooleanArray

fun main() {
    System.setOut(PrintStream(stdout))
    val t = scanner.nextInt()
    repeat(t) {
        val len = scanner.nextInt()
        str = scanner.next().toCharArray()
        str.sort()

        res = CharArray(len)
        used = BooleanArray(len)
        dfs(0, len)
    }
}

fun dfs(i: Int, len: Int) {
    if (i == len) {
        println(String(res))
        return
    }
    for (j in 0 until len) {
        if (!used[j]) {
            used[j] = true
            res[i] = str[j]
            dfs(i + 1, len)
            used[j] = false
        }
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