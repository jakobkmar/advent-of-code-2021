fun main() = day(10) {
    val failScores = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137
    )

    val closeScores = mapOf(
        ')' to 1,
        ']' to 2,
        '}' to 3,
        '>' to 4
    )

    val opening = listOf('(', '[', '{', '<')
    val closing = listOf(')', ']', '}', '>')

    part1 {
        var score = 0

        inputLines.forEach { line ->
            val awaiting = ArrayDeque<Char>()
            for (it in line) {
                if (it in opening)
                    awaiting.add(it)
                else if (it in closing) {
                    val expected = awaiting.removeLastOrNull()
                    if (opening.indexOf(expected) != closing.indexOf(it)) {
                        score += failScores[it]!!
                        break
                    }
                }
            }
        }

        score
    }

    part2 {
        val incomplete = inputLines.filterNot { line ->
            val awaiting = ArrayDeque<Char>()
            for (it in line) {
                if (it in opening)
                    awaiting.add(it)
                else if (it in closing)
                    if (opening.indexOf(awaiting.removeLastOrNull()) != closing.indexOf(it))
                        return@filterNot true
            }
            false
        }

        val scores = ArrayList<Long>()

        incomplete.forEach { line ->
            val awaiting = ArrayDeque<Char>()
            for (it in line) {
                if (it in opening)
                    awaiting.add(it)
                else if (it in closing)
                    awaiting.removeLastOrNull()
            }
            scores += awaiting.toList().reversed().fold(0L) { acc, c ->
                acc * 5L + closeScores[closing[opening.indexOf(c)]]!!.toLong()
            }
        }

        scores.apply { sort() }.let { it[(it.size / 2)] }
    }

    expectPart1 = 26397
    expectPart2 = 288957L
}
