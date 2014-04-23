package com.mkrcah

import java.io.File
import javax.imageio.ImageIO


object Main extends App {

    val complexRegion = Region2c(Complex(-2, 1), Complex(1, -1))

    val imageWidth = 500
    val imageHeight = Math.abs(imageWidth * complexRegion.size.height / complexRegion.size.width).toInt
    val size = Size(imageWidth, imageHeight)

    val renderer = new MandelbrotRenderer(size, complexRegion, GrayscalePalette)
    var image = renderer.render()
    ImageIO.write(image, "png", new File("out/saved.png"))

}


