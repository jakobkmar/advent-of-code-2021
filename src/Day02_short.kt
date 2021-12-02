fun main() = day(2) {
    fun stringToIntPairs() =
        inputLines.map { line -> line.split(' ').let { it[0][0] to it[1].toInt() } }

    part1 {
        stringToIntPairs().groupBy({ it.first }) { it.second }.mapValues { it.value.sum() }
            .let { it['f']!! * (it['d']!! - it['u']!!) }
    }

    part2 {
        var horizontal = 0; var depth = 0
        stringToIntPairs().fold(0) { acc, (a, i) ->
            if (a == 'f') {
                horizontal += i
                depth += acc * i
            }
            acc + (if (a == 'd') i else if (a == 'u') -i else 0)
        }
        horizontal * depth
    }

    expectPart1 = 150
    expectPart2 = 900
}
