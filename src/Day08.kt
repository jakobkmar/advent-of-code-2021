fun main() = day(8) {
    fun resolveInput() = inputLines.map { part ->
        part.split(" | ").map { it.split(' ') }.let { it.first() to it.last() }
    }

    part1 {
        resolveInput().sumOf { pair ->
            pair.second.count {
                when (it.length) {
                    2, 3, 4, 7 -> true
                    else -> false
                }
            }
        }
    }

    part2 {
        resolveInput().sumOf { pair ->
            val newMappings = HashMap<Set<Char>, Int>()
            fun mappingForInt(i: Int) = newMappings.toList().firstOrNull { it.second == i }?.first

            while (newMappings.size < 10) {
                pair.first.forEach { segment ->
                    when (segment.length) {
                        2 -> 1
                        3 -> 7
                        4 -> 4
                        7 -> 8
                        5 -> {
                            val segList = segment.toList()
                            when {
                                (segList intersect (mappingForInt(7) ?: return@forEach)).size == 3 -> 3
                                (segList intersect (mappingForInt(6) ?: return@forEach)).size == 5 -> 5
                                else -> 2
                            }
                        }
                        6 -> {
                            val segList = segment.toList()
                            if ((segList intersect (mappingForInt(7) ?: return@forEach)).size == 3) {
                                if ((segList intersect (mappingForInt(3) ?: return@forEach)).size == 5)
                                    9
                                else
                                    0
                            } else 6
                        }
                        else -> error("invalid segment length")
                    }.let {
                        newMappings[segment.toHashSet()] = it
                    }
                }
            }


            pair.second.map { segment ->
                newMappings[segment.toHashSet()]!!
            }.joinToString("").toInt()
        }
    }

    expectPart1 = 26
    expectPart2 = 61229
}
