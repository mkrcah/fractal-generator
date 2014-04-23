package com.mkrcah.fractals

import scala.annotation.tailrec

trait Fractal {
    def getEscapeTimeFor(c:Complex): Int
    def escapeTimeMax(): Int
}


case class Mandelbrot(escapeTimeMax: Int) extends Fractal {

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


case class Julia(escapeTimeMax: Int, c:Complex) extends Fractal {

    override def getEscapeTimeFor(z: Complex): Int = {

        val EscapeRadiusSqr = 4

        @tailrec
        def getEscapeTime(counter:Int, z:Complex):Int = {
            if (z.absSqr > EscapeRadiusSqr) counter
            else if (counter == escapeTimeMax) escapeTimeMax
            else getEscapeTime(counter+1, z*z+c)
        }

        getEscapeTime(0, z)

    }
}
