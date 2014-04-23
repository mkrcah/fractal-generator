package com.mkrcah

import javax.imageio.ImageIO
import java.io.File
import com.mkrcah.fractals._
import java.awt.image.BufferedImage
import com.mkrcah.fractals.Size
import com.mkrcah.fractals.Region2c
import com.mkrcah.fractals.Complex

object Main {

    val ComplexRegionToRender = Region2c(Complex(-2, 1), Complex(1, -1))
    
    val ImageWidth = 1000
    val ImageHeight = Math.abs(ImageWidth * ComplexRegionToRender.hwRatio).toInt
    val ImageSize = Size(ImageWidth, ImageHeight)


    def main(args: Array[String]) {

        val img = new BufferedImage(ImageSize.width, ImageSize.height, BufferedImage.TYPE_INT_RGB)
            with DrawableImage

        val renderer = new Mandelbrot(ImageSize, ComplexRegionToRender, HuePalette)

        println("Rendering started")
        img.draw(renderer.getColorFor)

        val filename = "sample-outputs/mandelbrot-zoom.png"
        println(s"Rendering finished, saving results to $filename")
        ImageIO.write(img, "png", new File(filename))

    }


}
