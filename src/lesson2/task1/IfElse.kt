@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.max
import kotlin.math.sqrt
import kotlin.math.abs
import kotlin.math.min

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 * 1 -- год 2, 3, 4 -- года
 * 5, 6, 7, 8, 9, 10, 11, 12, и все остальные "-надцать", а также 20 -- лет
 * 21 -- год 22, 23, 24 -- года и т.д.
 */
fun ageDescription(age: Int): String {
    return when {

        age % 10 in (5..20) || age % 100 in (5..20) -> "$age лет"
        age % 10 == 1 || age % 100 == 1 -> "$age год"
        age % 10 in (2..4) || age % 100 in (2..4) -> "$age года"

        else -> "$age лет"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val way1 = t1 * v1
    val way2 = t2 * v2
    val way3 = t3 * v3
    val halfway = (way1 + way2 + way3) / 2

    if (halfway <= way1) return (halfway / v1)
    else if (halfway >= way1 && halfway <= way1 + way2) return (t1 + (halfway - way1) / v2)
    else if (halfway >= way2 && halfway <= way1 + way2 + way3) return (t1 + t2 + (halfway - way1 - way2) / v3)
    else return Double.NaN
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    return when {
        (kingX == rookX1 || kingY == rookY1) && (kingX == rookX2 || kingY == rookY2) -> 3
        kingX == rookX1 || kingY == rookY1 -> 1
        kingX == rookX2 || kingY == rookY2 -> 2

        else -> 0
    }

}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int  {
    return when {
        (kingX == rookX || kingY == rookY) && (abs(kingX - bishopX) == abs(kingY - bishopY))-> 3
        kingX == rookX || kingY == rookY -> 1
        Math.abs(kingX - bishopX) == abs(kingY - bishopY) -> 2
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
с² < a²+b² остроугольный 0
с²=a²+b² прямоугольный 1
с² > a²+b² тупоугольный 2
с < a+b
c > a- b ( гдеc > а > b)
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    if (c >= a && c >= b && c < a + b) {
        return when {
            sqr(a) + sqr(b) - sqr(c) > 0.0 -> 0
            sqr(a) + sqr(b) - sqr(c) == 0.0 -> 1
            sqr(a) + sqr(b) - sqr(c) < 0.0 -> 2
            else -> -1
        }
    }
    else if (b >= a && b >= c && a + c > b) {
        return when {
            sqr(a) + sqr(c) - sqr(b) > 0.0 -> 0
            sqr(a) + sqr(c) - sqr(b) == 0.0 -> 1
            sqr(a) + sqr(c) - sqr(b) < 0.0 -> 2
            else -> -1
        }
    }
    else if (a >= b && a >= c && b + c > a) {
        return when {
            sqr(b) + sqr(c) - sqr(a) > 0.0 -> 0
            sqr(b) + sqr(c) - sqr(a) == 0.0 -> 1
            sqr(b) + sqr(c) - sqr(a) < 0.0 -> 2
            else -> -1
        }
    }
    else return -1

}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {

    if(b >= a && d >= c){
        return max(-1,min(b,d)-max(a,c))
    }
    else return -1

}



