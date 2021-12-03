import kotlin.math.pow

fun main() = day(3) {
    part1 {
        val xorBy = 2.0.pow(inputLines.first().length).toInt() - 1
        inputLines.map { it.withIndex() }.flatten().groupBy { it.index }
            .values.map { l -> l.sortedBy { it.value }[l.size / 2].value }
            .joinToString("").toInt(2).let { it * (it xor xorBy) }
    }

    fun part2(lines: List<String>, index: Int = 0, selector: (List<Pair<Char, Int>>) -> Char): List<String> {
        if (lines.size == 1) return lines
        val filterBy = selector(lines.groupingBy { it[index] }.eachCount().toList().reversed())
        return part2(lines.filter { it[index] == filterBy }, index + 1, selector)
    }

    part2 {
        val oxygen = part2(inputLines) { p -> p.maxByOrNull { it.second }!!.first }.first().toInt(2)
        val co2 = part2(inputLines) { p -> p.minByOrNull { it.second }!!.first }.first().toInt(2)
        oxygen * co2
    }

    expectPart1 = 22 * 9
    expectPart2 = 23 * 10
}
