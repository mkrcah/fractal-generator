package com.mkrcah

import javax.imageio.ImageIO
import java.io.File
import com.mkrcah.fractals._
import com.mkrcah.fractals.{Size, Region2c, Complex}

object Main {

    def main(args: Array[String]) {

        val complexRegion = Region2c(Complex(-2, 1), Complex(1, -1))

        val imageWidth = 500
        val imageHeight = Math.abs(imageWidth * complexRegion.size.height / complexRegion.size.width).toInt
        val size = Size(imageWidth, imageHeight)

        val renderer = new MandelbrotRenderer(size, complexRegion, GrayscalePalette)

        println("Rendering started")
        val image = renderer.render()
        val filename = "mandelbrot.png"
        ImageIO.write(image, "png", new File(filename))

        println(s"Rendering finished, result saved to $filename")
    }


}
