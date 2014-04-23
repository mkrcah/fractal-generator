package com.mkrcah


abstract class Color {
    def toRGB : ColorRGB
}



case class ColorRGB(r: Int, g:Int, b:Int) extends Color {

    override def toRGB: ColorRGB = this

    def toInt = r << 16 | g << 8 | b


}



case class ColorHSL(h: Double, l: Double, s: Double) extends Color {

    override def toRGB: ColorRGB = {

        val h1 = h / 60.0
        val c = (1 - math.abs(2 * l - 1)) * s
        val hmod2 = h1 - 2 * (h1 / 2).toInt
        val x = c * (1 - math.abs(hmod2 - 1))
        val ci:Int = (255 * c).toInt
        val xi:Int = (255 * x).toInt

        def toRGB(r:Double,g:Double,b:Double) =
            ColorRGB((r * 256).toInt, (g*256).toInt, (b*256).toInt)

        h1.floor.toInt % 5 match {
            case 0 => toRGB(ci, xi, 0)
            case 1 => toRGB(xi, ci, 0)
            case 2 => toRGB(0, ci, xi)
            case 3 => toRGB(0, xi, ci)
            case 4 => toRGB(xi, 0, ci)
            case 5 => toRGB(ci, 0, xi)
        }

    }
}

