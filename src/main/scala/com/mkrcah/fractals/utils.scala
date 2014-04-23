package com.mkrcah.fractals

/** Complex number **/
case class Complex(re: Double, im: Double) {
     def +(y: Complex) = Complex(re + y.re, im + y.im)
     def *(y: Complex) = Complex(re*y.re - im*y.im, im*y.re + re*y.im)
     def absSquared = re*re + im*im
 }

/** Point in 2d discrete space **/
case class Point2i(x: Int, y:Int)

case class Size[T](width: T, height: T)


case class Region2c(tl: Complex, br: Complex) {
    val width = br.re - tl.re
    val height = br.im - tl.im
}

case class Region2i(tl: Point2i, br: Point2i) {
    def size = Size[Double](width = br.x - tl.x, height = br.y - tl.y)
}

