package com.mkrcah.fractals

abstract class Palette {

    def get(index:Int): ColorRGB = {
        require(index >= 0 && index <= maxIndex, "s\"Index=$index out of range 0..$maxIndex\"")
        getByIndex(index)
    }

    def size:Int = 256
    def maxIndex = size - 1
    def getByIndex(i: Int): ColorRGB

}

object GrayscalePalette extends Palette {
    def getByIndex(i: Int) = ColorRGB(i, i, i)
}

object HuePalette extends Palette {
    def getByIndex(i: Int) = ColorHSL(i / 255.0 * 360, 0.5, 1).toRGB
}

object CyanPalette extends Palette {
    def getByIndex(i: Int)= ColorRGB(0, i *5 % 256, i *5 % 256)
}
