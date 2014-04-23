package com.mkrcah.fractals


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

        val EscapeRadius = 2
        def mapping(z: Complex, c:  Complex) = z * z + c

        var res = Complex(0,0)
        var i = 0

        while (i < EscapeTimeMax && res.absSquared <= EscapeRadius*EscapeRadius) {
            res = mapping(res, c)
            i = i + 1
        }
        i
    }


}


