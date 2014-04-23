package com.mkrcah

import javax.imageio.ImageIO
import java.io.{PrintWriter, File}
import com.mkrcah.fractals._
import com.mkrcah.fractals.Size2i
import com.mkrcah.fractals.Region2c
import com.mkrcah.fractals.Complex

object Main {



    def main(args: Array[String]) {

        val regionToRender = Region2c(Complex(-2, 1), Complex(1, -1))
        val fractal = new Mandelbrot(escapeTimeMax = 300)

        // Render text
        val textWidth = 100
        val textHeight = (textWidth * regionToRender.hwRatio).toInt
        val textRenderer = new TextRenderer(
            imgSize = Size2i(textWidth, textHeight),
            region = regionToRender,
            fractal = fractal,
            charMapper = if (_) '*' else ' ')
        val textFile = new PrintWriter("sample-outputs/mandelbrot.txt")
        textRenderer.render().foreach(textFile.println)
        textFile.close()


        // Render image
        val ImageWidth = 1000
        val ImageHeight = (ImageWidth * regionToRender.hwRatio).toInt
        val renderer = new ImageRenderer(
            imgSize = Size2i(ImageWidth, ImageHeight),
            region = regionToRender,
            pal = HuePalette,
            fractal = new Mandelbrot(escapeTimeMax = 300))

        println("Rendering started")
        val img = renderer.render()
        println(s"Rendering finished")

        val filename = "sample-outputs/mandelbrot.png"
        ImageIO.write(img, "png", new File(filename))
        println(s"Output saved to $filename")


//

    }


}
