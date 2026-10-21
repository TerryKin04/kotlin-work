package triangles

import kotlin.math.sqrt

typealias Triangle = Triple<Double, Double, Double>

fun isValidTriangle(t: Triangle): Boolean {
    val (a, b, c) = t
    return a > 0 && b > 0 && c > 0 &&
            a < b + c &&
            b < a + c &&
            c < a + b
}

fun triangleArea(t: Triangle): Double {
    require(isValidTriangle(t)) { "Side lengths do not form a valid triangle" }
    val (a, b, c) = t
    val s = (a + b + c) / 2.0
    return sqrt(s * (s - a) * (s - b) * (s - c))
}