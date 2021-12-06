import kotlin.math.max
import kotlin.math.min

fun main() = day(5) {
    part1 {
        val lines = inputLines.map {
            it.split("->").map {
                it.trim().split(',').map { it.toInt() }
            }.let {
                it.first().let { it.first() to it.last() } to it.last().let { it.first() to it.last() }
            }
        }.filter { (it.first.first == it.second.first) xor (it.first.second == it.second.second) }

        val sizeX = lines.maxOf { max(it.first.first, it.second.first) } + 1
        val sizeY = lines.maxOf { max(it.first.second, it.second.second) } + 1

        val grid = IntArray(sizeX * sizeY)
        fun increase(x: Int, y: Int) {
            grid[x + y * sizeY] = grid[x + y * sizeY] + 1
        }

        lines.forEach {
            when {
                it.first.first == it.second.first -> {
                    (min(it.first.second, it.second.second)..max(it.first.second, it.second.second)).forEach { pos ->
                        increase(it.first.first, pos)
                    }
                }
                it.first.second == it.second.second -> {
                    (min(it.first.first, it.second.first)..max(it.first.first, it.second.first)).forEach { pos ->
                        increase(pos, it.first.second)
                    }
                }
            }
        }

        grid.count { it >= 2 }
    }

    part2 {
        val lines = inputLines.map {
            it.split("->").map {
                it.trim().split(',').map { it.toInt() }
            }.let {
                it.first().let { it.first() to it.last() } to it.last().let { it.first() to it.last() }
            }
        }

        val sizeX = lines.maxOf { max(it.first.first, it.second.first) } + 1
        val sizeY = lines.maxOf { max(it.first.second, it.second.second) } + 1

        val grid = IntArray(sizeX * sizeY)
        fun increase(x: Int, y: Int) {
            grid[x + y * sizeY] = grid[x + y * sizeY] + 1
        }

        lines.forEach {
            when {
                it.first.first == it.second.first && it.first.second != it.second.second -> {
                    (min(it.first.second, it.second.second)..max(it.first.second, it.second.second)).forEach { pos ->
                        increase(it.first.first, pos)
                    }
                }
                it.first.second == it.second.second && it.first.first != it.second.first -> {
                    (min(it.first.first, it.second.first)..max(it.first.first, it.second.first)).forEach { pos ->
                        increase(pos, it.first.second)
                    }
                }
                else -> {
                    val xRange = when {
                        it.first.first > it.second.first -> (it.first.first downTo it.second.first)
                        else -> it.first.first..it.second.first
                    }
                    val x = (xRange).iterator()
                    val y = when {
                        it.first.second > it.second.second -> (it.first.second downTo it.second.second).iterator()
                        else -> (it.first.second..it.second.second).iterator()
                    }
                    repeat(xRange.count()) {
                        increase(x.next(), y.next())
                    }
                }
            }
        }

        grid.toList().windowed(sizeX, sizeX).forEach {
            println(it.joinToString("").replace('0', '.'))
        }

        grid.count { it >= 2 }
    }

    expectPart1 = 5
    expectPart2 = 12
}
