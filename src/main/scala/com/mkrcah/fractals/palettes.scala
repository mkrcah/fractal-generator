package com.mkrcah.fractals

abstract class Palette {
    def get(index:Int): ColorRGB
}

object Palette1 extends Palette {
    def get(i:Int)= ColorRGB(0, i *5 % 256, i *5 % 256)
}

object GrayscalePalette extends Palette {
    def get(i: Int) = ColorRGB(i, i, i)
}

object HuePalette extends Palette {
    def get(i: Int) = ColorHSL(i / 256.0 * 360, 0.5, 1).toRGB
}

