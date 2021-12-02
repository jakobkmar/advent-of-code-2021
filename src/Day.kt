import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyles
import com.github.ajalt.mordant.terminal.Terminal

fun day(number: Int, builder: Day.() -> Unit) {
    Day(number).apply(builder).run()
}

class Day(private val number: Int) {
    private fun inputFileName(extra: String = "") = "Day${number.toString().padStart(2, '0')}${extra}.txt"

    lateinit var input: String
    val inputLines get() = input.lines()
    val inputInts get() = inputLines.map { it.toInt() }

    private var part1: (() -> Any?)? = null
    private var part2: (() -> Any?)? = null

    var expectPart1: Any? = null
    var expectPart2: Any? = null

    private val terminal = Terminal()

    fun part1(block: () -> Any?) {
        part1 = block
    }

    fun part2(block: () -> Any?) {
        part2 = block
    }

    private fun testPart(partName: String, part: (() -> Any?)?, expected: Any?) {
        if (part != null && expected != null) {
            val actual = part.invoke()
            if (actual != expected) {
                terminal.println("${TextColors.red("Failed")} test for $partName! Expected '$expected' but got '$actual' instead")
            } else {
                terminal.println("The result of $partName is ${TextColors.green("correct")}.")
            }
        }
    }

    private fun runPart(partName: String, part: (() -> Any?)?) {
        if (part != null)
            terminal.println("The result of $partName is ${TextStyles.bold(TextColors.brightCyan(part.invoke().toString()))}")
    }

    fun run() {
        println("Running ${TextColors.brightMagenta("Day $number")}")

        println()
        terminal.println(TextColors.gray("Running against tests..."))
        println()

        input = this::class.java.getResourceAsStream(inputFileName("_test"))!!.bufferedReader().readText()
        testPart("part1", part1, expectPart1)
        testPart("part2", part2, expectPart2)

        input = this::class.java.getResourceAsStream(inputFileName())!!.bufferedReader().readText()
        println()
        terminal.println(TextColors.gray("Running against real input..."))
        println()

        runPart("part1", part1)
        runPart("part2", part2)
    }
}
