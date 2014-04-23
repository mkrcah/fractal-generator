package com.mkrcah

import javax.imageio.ImageIO
import java.io.File
import com.mkrcah.fractals._
import com.mkrcah.fractals.{Size, Region2c, Complex}

object Main {

    def main(args: Array[String]) {

        val complexRegion = Region2c(Complex(-2, 1), Complex(1, -1))

        val imageWidth = 1000
        val imageHeight = Math.abs(imageWidth * complexRegion.height / complexRegion.width).toInt
        val size = Size(imageWidth, imageHeight)

        val renderer = new MandelbrotRenderer(size, complexRegion, HuePalette)

        println("Rendering started")

        val image = renderer.render()
        val filename = "sample-outputs/mandelbrot-zoom.png"
        ImageIO.write(image, "png", new File(filename))

        println(s"Rendering finished, result saved to $filename")
    }


}
