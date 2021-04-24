package com.hrithik.snakes

import android.graphics.Bitmap
import android.graphics.Rect

class PartSnake(var bm:Bitmap, var x:Int, var y:Int) {

    var rBody = Rect(x, y, x+GameView.sizeOfMap, y+GameView.sizeOfMap)
    var rTop = Rect(x, y-10*Constants.SCREEN_HEIGHT/1920, x+GameView.sizeOfMap, y)
    var rBottom = Rect(x, y+GameView.sizeOfMap, x+GameView.sizeOfMap, y+GameView.sizeOfMap+10*Constants.SCREEN_HEIGHT/1920)
    var rLeft = Rect(x-10*Constants.SCREEN_WIDTH/1080, y, x, y+GameView.sizeOfMap)
    var rRight = Rect(x+GameView.sizeOfMap, y, x+GameView.sizeOfMap+10*Constants.SCREEN_WIDTH/1080, y+GameView.sizeOfMap)

}