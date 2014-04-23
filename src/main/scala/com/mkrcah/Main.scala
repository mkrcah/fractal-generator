package com.mkrcah

import javax.imageio.ImageIO
import java.io.File
import com.mkrcah.fractals._
import com.mkrcah.fractals.Size2i
import com.mkrcah.fractals.Region2c
import com.mkrcah.fractals.Complex

object Main {



    def main(args: Array[String]) {

        val ComplexRegionToRender = Region2c(Complex(-2, 1), Complex(1, -1))
        val ImageWidth = 1000
        val ImageHeight = Math.abs(ImageWidth * ComplexRegionToRender.hwRatio).toInt

        val renderer = new ImageFractalRenderer(
            imgSize = Size2i(ImageWidth, ImageHeight),
            region = ComplexRegionToRender,
            pal = HuePalette,
            fractal = new MandelbrotSet(escapeTimeMax = 300))

        println("Rendering started")
        val img = renderer.render()
        println(s"Rendering finished")

        val filename = "sample-outputs/mandelbrot-zoom.png"
        ImageIO.write(img, "png", new File(filename))
        println(s"Output saved to $filename")

    }


}
