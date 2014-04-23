package com.mkrcah

import javax.imageio.ImageIO
import java.io.{PrintWriter, File}
import com.mkrcah.fractals._
import com.mkrcah.fractals.Size2i
import com.mkrcah.fractals.Region2c
import com.mkrcah.fractals.Complex

object Main {


    def OutputDir = "sample-outputs"

    def main(args: Array[String]) {

        val imgHeight = 1000

        // Mandelbrot: Render image
        val regMandelbrot = Region2c(Complex(-2.1, 1.3), Complex(0.8, -1.3))
        val imgHeightMandelbrot = (imgHeight * regMandelbrot.hwRatio).toInt
        val fractal = new Mandelbrot(escapeTimeMax = 300)
        val renderer = new ImageRenderer(
            imgSize = Size2i(imgHeight, imgHeightMandelbrot),
            region = regMandelbrot,
            pal = HuePalette,
            fractal = new Mandelbrot(escapeTimeMax = 300))
        save(renderer, "mandelbrot.png")

        // Mandelbrot: Render text
        val textWidth = 100
        val textHeight = (textWidth * regMandelbrot.hwRatio).toInt
        val textRenderer = new TextRenderer(
            imgSize = Size2i(textWidth, textHeight),
            region = regMandelbrot,
            fractal = fractal,
            charMapper = if (_) '*' else ' ')
        save(textRenderer, "mandelbrot.txt")


        // Julia: Render images
        val regionJulia = Region2c(Complex(-2, 1.2), Complex(2, -1.2))
        val imageHeightJulia = (imgHeight * regionJulia.hwRatio).toInt
        val cParams = Array(
            Complex(-0.4, 0.6),
            Complex(0.285,0.01),
            Complex(-0.835,-0.2321))

        cParams.foreach((c) => {
            val renderer = new ImageRenderer(
                imgSize = Size2i(imgHeight, imageHeightJulia),
                region = regionJulia,
                pal = HuePalette,
                fractal = new Julia(escapeTimeMax = 300, c=c))
            save(renderer, s"julia(${c.re},${c.im}).png")
        })

    }
    
    def save(renderer:ImageRenderer, filename: String) {
        println(s"Rendering of $filename started")
        val img = renderer.render()
        ImageIO.write(img, "png", new File(s"$OutputDir/$filename"))
        println(s"Output saved to $filename")
    }
    
    def save(renderer:TextRenderer, filename: String) {
        println(s"Rendering of $filename started")
        val textFile = new PrintWriter(s"$OutputDir/$filename")
        renderer.render().foreach(textFile.println)
        println(s"Output saved to $filename")
        textFile.close()
    }


}
