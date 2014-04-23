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
    }
}

