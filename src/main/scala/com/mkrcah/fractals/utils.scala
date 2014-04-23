package com.mkrcah.fractals

/** Complex number **/
case class Complex(re: Double, im: Double) {
     def +(y: Complex) = Complex(re + y.re, im + y.im)
     def *(y: Complex) = Complex(re*y.re - im*y.im, im*y.re + re*y.im)
     def absSqr = re*re + im*im
 }

/** Point in 2d discrete space **/
case class Point2i(x: Int, y:Int)

case class Size[T](width: T, height: T)

case class Region2c(tl: Complex, br: Complex) {
    val width = br.re - tl.re
    val height = br.im - tl.im
    val hwRatio = height / width
}

case class Region2i(tl: Point2i, br: Point2i) {
    val width = br.x - tl.x
    val height = br.y - tl.y
}


