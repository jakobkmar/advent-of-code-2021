fun main() = day(6) {
    fun resolveInput() = input.split(',').groupBy { it.toLong() }.mapValues { it.value.size.toLong() }

    fun model(days: Int, table: Map<Long, Long> = resolveInput()): Long {
        if (days == 0) return table.map { it.value }.sum()
        return model(
            days - 1,
            buildMap {
                val ticked = table.mapKeys { it.key - 1 }
                val newCount = ticked[-1] ?: 0
                putAll(ticked.filter { it.key >= 0 })
                put(6, newCount + (get(6) ?: 0))
                put(8, newCount)
            }
        )
    }

    part1 {
        model(80)
    }

    part2 {
        model(256)
    }

    expectPart1 = 5934L
    expectPart2 = 26984457539L
}
