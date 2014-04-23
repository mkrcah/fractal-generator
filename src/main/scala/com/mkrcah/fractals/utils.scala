package com.mkrcah.fractals

import java.awt.image.BufferedImage

/** Complex number **/
case class Complex(re: Double, im: Double) {
     def +(y: Complex) = Complex(re + y.re, im + y.im)
     def *(y: Complex) = Complex(re*y.re - im*y.im, im*y.re + re*y.im)
     def absSqr = re*re + im*im
 }

/** Point in 2d discrete space **/
case class Point2i(x: Int, y:Int)

case class Size2i(width: Int, height: Int)

case class Region2c(tl: Complex, br: Complex) {
    val width = br.re - tl.re
    val height = br.im - tl.im
    val hwRatio = Math.abs(height / width)
}

case class Region2i(tl: Point2i, br: Point2i) {
    val width = br.x - tl.x
    val height = br.y - tl.y
}



trait DrawableImage extends BufferedImage {

    def getPixels: Iterable[Point2i] = {
        for (y <- 0 to getHeight - 1; x <- 0 to getWidth - 1)
        yield Point2i(x, y)
    }

    def draw(f: Point2i => ColorRGB) = {
        for (pixelCoords <- getPixels) {
            val color = f(pixelCoords)
            setRGB(pixelCoords.x, pixelCoords.y, color.toInt)
        }
        this
    }
}


