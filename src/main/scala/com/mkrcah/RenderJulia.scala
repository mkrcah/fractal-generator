package com.mkrcah

import javax.imageio.ImageIO
import java.io.File
import com.mkrcah.fractals._

object RenderJulia {


    def OutputDir = "sample-outputs"

    def main(args: Array[String]) {

        val region = Region2c(Complex(-2, 1.2), Complex(2, -1.2))
        val imgHeight = 1000
        val imageHeightJulia = (imgHeight * region.hwRatio).toInt

        // interesting Julia params
        val inits = Array(
            Complex(-0.4, 0.6),
            Complex(0.285,0.01),
            Complex(-0.835,-0.2321)
        )

        inits.foreach((init) => {

            val renderer = new ImageRenderer(
                imgSize = Size2i(imgHeight, imageHeightJulia),
                region = region,
                pal = HuePalette,
                fractal = new Julia(init))

            save(renderer, s"julia(${init.re},${init.im}).png")
        })

    }
    
    def save(renderer:ImageRenderer, filename: String) {
        println(s"Rendering of $filename started")
        val img = renderer.render()
        ImageIO.write(img, "png", new File(s"$OutputDir/$filename"))
        println(s"Output saved to $filename")
    }
    


}
