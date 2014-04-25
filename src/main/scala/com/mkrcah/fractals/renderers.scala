package com.mkrcah.fractals

import java.awt.image.BufferedImage


abstract class Renderer(imageSize: Size2i, region: Region2c, fractal:Fractal) {

    protected val escapeTimeMax = fractal.escapeTimeMax

    protected def getEscapeTime(p:Point2i): Int =  {
        val c = imgPointToComplex(p)
        fractal.getEscapeTimeFor(c)
    }

    protected def imgPointToComplex(pixel: Point2i) = Complex(
        pixel.x.toFloat / imageSize.width * region.width + region.tl.re,
        pixel.y.toFloat / imageSize.height * region.height + region.tl.im
    )

}


class ImageRenderer(imgSize: Size2i, region: Region2c, pal: Palette, fractal:Fractal)
    extends Renderer(imgSize, region, fractal){

    def render(): BufferedImage = {
        val img = new BufferedImage(imgSize.width, imgSize.height, BufferedImage.TYPE_INT_RGB)
            with DrawableImage
        img.draw(getColorFor)
    }

    def getColorFor(pixel: Point2i): ColorRGB = {
        val escapeTime = getEscapeTime(pixel)
        val colorIdx =
            if (escapeTime < escapeTimeMax) (escapeTime.toFloat / escapeTimeMax * pal.maxIndex).toInt
            else 0
        pal.get(colorIdx)
    }
}


class TextRenderer(imgSize: Size2i, region: Region2c, fractal:Fractal, charMapper: Boolean => Char)
    extends Renderer(imgSize, region, fractal) {

    def render(): Array[String] = {

        def mapToChar(y:Int,x:Int) = {
            val inSet = getEscapeTime(new Point2i(x,y)) == escapeTimeMax
            charMapper(inSet)
        }

        Array
            .tabulate(imgSize.height,imgSize.width)(mapToChar)
            .map(_.mkString(" "))


    }



}





