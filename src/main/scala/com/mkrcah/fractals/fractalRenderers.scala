package com.mkrcah.fractals

import java.awt.image.BufferedImage


trait DrawableImage extends BufferedImage {

    def getPixels: Iterable[Point2i] = {
        for (y <- 0 to getHeight - 1; x <- 0 to getWidth - 1)
        yield Point2i(x, y)
    }

    def draw(f: Point2i => ColorRGB) = {
        for (pixelCoords <- getPixels) {
            val color = f(pixelCoords)
            setRGB(pixelCoords.x, pixelCoords.y, color.toInt)
        }
        this
    }
}



abstract class FractalRenderer(imageSize: Size2i) {

    protected def discreteToComplex(pixel: Point2i, area: Region2c) = Complex(
        pixel.x.toFloat / imageSize.width * area.width + area.tl.re,
        pixel.y.toFloat / imageSize.height * area.height + area.tl.im
    )

}


class ImageFractalRenderer(imgSize: Size2i, region: Region2c, pal: Palette, fractal:FractalSet)
    extends FractalRenderer(imgSize){

    val escapeTimeMax = fractal.escapeTimeMax()

    def render(): BufferedImage = {
        val img = new BufferedImage(imgSize.width, imgSize.height, BufferedImage.TYPE_INT_RGB)
            with DrawableImage
        img.draw(getColorFor)
    }

    private def getColorFor(pixel: Point2i): ColorRGB = {

        val complexCoords = discreteToComplex(pixel, region)
        val escapeTime = fractal.getEscapeTimeFor(complexCoords)

        val colorIdx =
            if (escapeTime < escapeTimeMax) (escapeTime.toFloat / escapeTimeMax * pal.maxIndex).toInt
            else 0

        pal.get(colorIdx)
    }

}





