package com.mkrcah

import java.awt.image.BufferedImage



abstract class ImageRenderer(imgSize: Size[Int]) {

    def render(): BufferedImage = {

        val img = new BufferedImage(imgSize.width, imgSize.height, BufferedImage.TYPE_INT_RGB)

        for (y <- 0 to imgSize.height - 1;
             x <- 0 to imgSize.width - 1
        ) {
            val imgCoords = Point2i(x, y)
            val color = getColorFor(imgCoords)
            img.setRGB(x, y, color.toInt)
        }
        img
    }

    protected def getColorFor(imgCoords: Point2i): ColorRGB

}




class MandelbrotRenderer(imgSize: Size[Int], region: Region2c, palette: Palette)
    extends ImageRenderer(imgSize)
{

    override def getColorFor(imgCoords: Point2i): ColorRGB = {
        val complex = transformToComplex(imgCoords, region)
        val escapeTime = getEscapeTime(complex)
        palette.get(escapeTime)
    }

    private def transformToComplex(imgCoords: Point2i, area: Region2c)
        = Complex(
            imgCoords.x.toFloat / imgSize.width * area.size.width + area.tl.re,
            imgCoords.y.toFloat / imgSize.height * area.size.height + area.tl.im
        )


    private val ITER_MAX = 255
    private val ESCAPE_THRESHOLD_SQR = 2*2

    private def getEscapeTime(c:Complex):Int = {

        def mapping(z: Complex, c:  Complex) = z * z + c

        var res = Complex(0,0)
        var i = 0

        while (i < ITER_MAX && res.absSquared <= ESCAPE_THRESHOLD_SQR) {
            res = mapping(res, c)
            i = i + 1
        }
        i
    }


}


