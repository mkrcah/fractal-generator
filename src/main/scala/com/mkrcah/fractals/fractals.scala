package com.mkrcah.fractals

import scala.annotation.tailrec



abstract class Fractal(val accInit:(Complex)=>Complex, val recCall:(Complex,Complex)=>Complex,
                       val escapeTimeMax:Int, val escapeRadius:Int=2) {

    val EscapeRadiusSqr = escapeRadius * escapeRadius

    def getEscapeTimeFor(c: Complex): Int = {

        @tailrec
        def getEscapeTime(counter:Int, acc:Complex):Int = {
            if (acc.absSqr > EscapeRadiusSqr) counter
            else if (counter == escapeTimeMax) escapeTimeMax
            else getEscapeTime(counter+1, recCall(c, acc))
        }

        getEscapeTime(0, accInit(c))

    }

}


class Mandelbrot(quality:Int=300) extends Fractal(
    accInit=(c)=>new Complex(0,0), recCall=(c,acc) => acc * acc + c, escapeTimeMax=quality )

class Julia(param:Complex, quality:Int=300) extends Fractal(
    accInit=(c)=>c, recCall = (_,acc) => acc*acc + param, escapeTimeMax=quality)

