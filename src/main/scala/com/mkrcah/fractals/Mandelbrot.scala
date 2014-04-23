package com.mkrcah.fractals

import scala.annotation.tailrec


class Mandelbrot(imgSize: Size[Int], region: Region2c, palette: Palette) {

    private val EscapeTimeMax = 300

    def getColorFor(imgCoords: Point2i): ColorRGB = {
        
        val complexCoords = transformToComplex(imgCoords, region)
        val escapeTime = getEscapeTime(complexCoords)

        val colorIdx =
            if (escapeTime < EscapeTimeMax) (escapeTime.toFloat / EscapeTimeMax * palette.maxIndex).toInt
            else 0

        palette.get(colorIdx)
    }

    private def transformToComplex(imgCoords: Point2i, area: Region2c)
        = Complex(
            imgCoords.x.toFloat / imgSize.width * area.width + area.tl.re,
            imgCoords.y.toFloat / imgSize.height * area.height + area.tl.im
        )


    private def getEscapeTime(c:Complex):Int = {

        val EscapeRadiusSqr = 4

        @tailrec
        def getEscapeTime(counter:Int, z:Complex):Int = {
            if (z.absSqr > EscapeRadiusSqr) counter
            else if (counter == EscapeTimeMax) EscapeTimeMax
            else getEscapeTime(counter+1, z*z+c)
        }

        getEscapeTime(0, new Complex(0,0))

    }


}


