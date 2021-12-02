fun main() = day(2) {
    fun List<String>.stringToIntPairs() =
        map { line -> line.split(' ').let { it[0] to it[1].toInt() } }

    part1 {
        var horizontal = 0; var depth = 0

        inputLines.stringToIntPairs().forEach { (action, x) ->
            when (action) {
                "forward" -> horizontal += x
                "down" -> depth += x
                "up" -> depth -= x
            }
        }

        horizontal * depth
    }

    part2 {
        var horizontal = 0; var depth = 0; var aim = 0

        inputLines.stringToIntPairs().forEach { (action, x) ->
            when (action) {
                "forward" -> {
                    horizontal += x
                    depth += aim * x
                }
                "down" -> aim += x
                "up" -> aim -= x
            }
        }

        horizontal * depth
    }

    expectPart1 = 150
    expectPart2 = 900
}
