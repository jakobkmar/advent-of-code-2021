fun main() = day(4) {
    class Board(val lines: MutableList<Int> = ArrayList()) {
        val crossed = BooleanArray(5 * 5)

        fun cross(num: Int): Boolean {
            lines.forEachIndexed { index, i ->
                if (i == num)
                    crossed[index] = true
            }

            // rows
            (0..4).forEach outer@{ y ->
                (0..4).forEach { x ->
                    if (!crossed[x + y * 5])
                        return@outer
                }
                return true
            }

            // columns
            (0..4).forEach outer@{ y ->
                (0..4).forEach { x ->
                    if (!crossed[x * 5 + y])
                        return@outer
                }
                return true
            }

            return false
        }

        fun sum(): Int {
            var score = 0
            lines.zip(crossed.toTypedArray()).forEach {
                if (!it.second)
                    score += it.first
            }
            return score
        }
    }

    fun resolveInput() = ArrayList<Board>().apply {
        var currentBoard = Board()
        inputLines.forEachIndexed { index, s ->
            if (index == 0) return@forEachIndexed

            if (s.isEmpty()) {
                if (currentBoard.lines.isNotEmpty())
                    this += currentBoard
                currentBoard = Board()
            }

            s.split(' ').filterNot { it.isEmpty() }.forEach {
                currentBoard.lines += it.toInt()
            }
        }
        this += currentBoard
    } to inputLines.first().split(',').map { it.toInt() }

    part1 {
        val (boards, nums) = resolveInput()

        nums.forEach { num ->
            boards.forEach {
                if (it.cross(num)) {
                    return@part1 it.sum() * num
                }
            }
        }
    }

    part2 {
        val (boards, nums) = resolveInput()

        nums.forEach { num ->
            for (it in ArrayList(boards)) {
                if (it.cross(num)) {
                    if (boards.size == 1)
                        return@part2 it.sum() * num
                    boards.remove(it)
                }
            }
        }
    }

    expectPart1 = 4512
    expectPart2 = 1924
}
