package com.mkrcah.fractals

abstract class Palette {

    def get(index:Int): ColorRGB = {
        if (index >= 0 && index <= size) getByIndex(index)
        else throw new IndexOutOfBoundsException(s"Index=$index out of range 0..$maxIndex")
    }

    def size: Int
    def maxIndex = size - 1
    def getByIndex(i: Int): ColorRGB


}

object Palette1 extends Palette {
    def size = 256
    def getByIndex(i: Int)= ColorRGB(0, i *5 % 256, i *5 % 256)
}

object GrayscalePalette extends Palette {
    def size = 256
    def getByIndex(i: Int) = ColorRGB(i, i, i)
}

object HuePalette extends Palette {
    def size = 256
    def getByIndex(i: Int) = ColorHSL(i / 255.0 * 360, 0.5, 1).toRGB
}

