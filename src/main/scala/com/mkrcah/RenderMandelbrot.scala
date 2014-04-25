package com.mkrcah

import javax.imageio.ImageIO
import java.io.{PrintWriter, File}
import com.mkrcah.fractals._

object RenderMandelbrot {


    def OutputDir = "sample-outputs"

    def main(args: Array[String]) {

        val region = Region2c(Complex(-2.1, 1.3), Complex(0.8, -1.3))
        val fractal = new Mandelbrot()

        // Render image
        val imgWidth = 1000
        val imgHeight = (imgWidth * region.hwRatio).toInt
        val renderer = new ImageRenderer(
            imgSize = Size2i(imgWidth, imgHeight),
            region = region, pal = HuePalette,
            fractal = new Mandelbrot())
        save(renderer, "mandelbrot.png")

        // Mandelbrot: Render text
        val textWidth = 100
        val textHeight = (textWidth * region.hwRatio).toInt
        val textRenderer = new TextRenderer(
            imgSize = Size2i(textWidth, textHeight),
            region = region,
            fractal = fractal,
            charMapper = if (_) '*' else ' ')
        save(textRenderer, "mandelbrot.txt")


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
