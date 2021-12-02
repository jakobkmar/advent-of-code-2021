fun main() = day(1) {
    part1 {
        inputInts.windowed(2).count { it[0] < it.last() }
    }

    part2 {
        inputInts.windowed(4).count { it[0] < it.last() }
    }

    expectPart1 = 7
    expectPart2 = 5
}
