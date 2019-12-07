fun main() {
    readLine()!!.split(" ").map { it.toInt() }.toIntArray().sum().also { println(it) }
}