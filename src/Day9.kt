fun main() = day(9) {
    class Board {
        val lines: List<List<Int>> = inputLines.map { it.map { it.digitToInt() } }

        val lowPoints = buildList {
            lines.forEachIndexed { lineIndex, line ->
                line.forEachIndexed lineCheck@{ index, i ->
                    if (index > 0 && line[index - 1] <= i) return@lineCheck
                    if (index != line.lastIndex && line[index + 1] <= i) return@lineCheck
                    if (lineIndex > 0 && lines[lineIndex - 1][index] <= i) return@lineCheck
                    if (lineIndex != lines.lastIndex && lines[lineIndex + 1][index] <= i) return@lineCheck
                    this += i to (index to lineIndex)
                }
            }
        }

        fun countBasin(x: Int, y: Int, counted: MutableList<Pair<Int, Int>> = ArrayList()): Int {
            if (counted.contains(x to y)) return 0 else counted += x to y
            if (x !in lines.first().indices || y !in lines.indices) return 0
            if (lines[y][x] == 9) return 0
            return 1 + countBasin(x + 1, y, counted) + countBasin(x, y + 1, counted) + countBasin(x - 1, y, counted) + countBasin(x, y - 1, counted)
        }
    }

    part1 {
        Board().lowPoints.sumOf { it.first + 1 }
    }

    part2 {
        val board = Board()
        board.lowPoints.map { it.second }
            .map { board.countBasin(it.first, it.second) }.sortedDescending().let { it[0] * it[1] * it[2] }
    }

    expectPart1 = 15
    expectPart2 = 1134
}
