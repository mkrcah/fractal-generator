package com.mkrcah.fractals

import scala.annotation.tailrec

trait FractalSet {
    def getEscapeTimeFor(c:Complex): Int
    def escapeTimeMax(): Int
}


case class MandelbrotSet(escapeTimeMax: Int) extends FractalSet {

    override def getEscapeTimeFor(c: Complex): Int = {

        val EscapeRadiusSqr = 4

        @tailrec
        def getEscapeTime(counter:Int, z:Complex):Int = {
            if (z.absSqr > EscapeRadiusSqr) counter
            else if (counter == escapeTimeMax) escapeTimeMax
            else getEscapeTime(counter+1, z*z+c)
        }

        getEscapeTime(0, new Complex(0,0))

    }
}
