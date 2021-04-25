package com.hrithik.snakes

import android.graphics.Bitmap
import android.graphics.Canvas

class Snake(bm: Bitmap, x: Int, y: Int, var length: Int) {

    private val bm_body_bottom_left = Bitmap.createBitmap(bm, 0, 0, bm.width / 14, bm.height)
    private val bm_body_bottom_right = Bitmap.createBitmap(bm, bm.width / 14, 0, bm.width / 14, bm.height)
    private val bm_body_horizontal = Bitmap.createBitmap(bm, 2 * bm.width / 14, 0, bm.width / 14, bm.height)
    private val bm_body_top_left = Bitmap.createBitmap(bm, 3 * bm.width / 14, 0, bm.width / 14, bm.height)
    private val bm_body_top_right = Bitmap.createBitmap(bm, 4 * bm.width / 14, 0, bm.width / 14, bm.height)
    private val bm_body_vertical = Bitmap.createBitmap(bm, 5 * bm.width / 14, 0, bm.width / 14, bm.height)
    private val bm_head_down = Bitmap.createBitmap(bm, 6 * bm.width / 14, 0, bm.width / 14, bm.height)
    private val bm_head_left = Bitmap.createBitmap(bm, 7 * bm.width / 14, 0, bm.width / 14, bm.height)
    private val bm_head_right = Bitmap.createBitmap(bm, 8 * bm.width / 14, 0, bm.width / 14, bm.height)
    private val bm_head_up = Bitmap.createBitmap(bm, 9 * bm.width / 14, 0, bm.width / 14, bm.height)
    private val bm_tail_up = Bitmap.createBitmap(bm, 10 * bm.width / 14, 0, bm.width / 14, bm.height)
    private val bm_tail_right = Bitmap.createBitmap(bm, 11 * bm.width / 14, 0, bm.width / 14, bm.height)
    private val bm_tail_left = Bitmap.createBitmap(bm, 12 * bm.width / 14, 0, bm.width / 14, bm.height)
    private val bm_tail_down = Bitmap.createBitmap(bm, 13 * bm.width / 14, 0, bm.width / 14, bm.height)

    var arrPartSnake = ArrayList<PartSnake>()
    var move_left = false
    var move_right = false
    var move_top = false
    var move_bottom = false

    init {
        arrPartSnake.add(PartSnake(bm_head_down, x, y))
        for (i in 1 until length - 1)
            arrPartSnake.add(PartSnake(bm_body_vertical, x, arrPartSnake[i - 1].y - GameView.sizeOfMap))
        arrPartSnake.add(PartSnake(bm_tail_up, x, arrPartSnake[length - 2].y - GameView.sizeOfMap))
        move_bottom = true
    }

    fun update() {
        for (i in length - 1 downTo 1) {
            arrPartSnake[i].x = (arrPartSnake[i - 1].x)
            arrPartSnake[i].y = (arrPartSnake[i - 1].y)
        }
        if (move_right) {
            arrPartSnake[0].x = (arrPartSnake[0].x + GameView.sizeOfMap)
            arrPartSnake[0].bm = (bm_head_right)
        } else if (move_bottom) {
            arrPartSnake[0].y = arrPartSnake[0].y + GameView.sizeOfMap
            arrPartSnake[0].bm = (bm_head_down)
        } else if (move_top) {
            arrPartSnake[0].y = (arrPartSnake[0].y - GameView.sizeOfMap)
            arrPartSnake[0].bm = (bm_head_up)
        } else {
            arrPartSnake[0].x = (arrPartSnake[0].x - GameView.sizeOfMap)
            arrPartSnake[0].bm = (bm_head_left)
        }
        for (i in 1 until length - 1) {
            if (arrPartSnake[i].rLeft.intersect(arrPartSnake[i + 1].rBody)
                    && arrPartSnake[i].rBottom.intersect(arrPartSnake[i - 1].rBody)
                    || arrPartSnake[i].rBottom.intersect(arrPartSnake[i + 1].rBody)
                    && arrPartSnake[i].rLeft.intersect(arrPartSnake[i - 1].rBody)) {
                arrPartSnake[i].bm = (bm_body_bottom_left)
            } else if (arrPartSnake[i].rLeft.intersect(arrPartSnake[i + 1].rBody)
                    && arrPartSnake[i].rTop.intersect(arrPartSnake[i - 1].rBody)
                    || arrPartSnake[i].rLeft.intersect(arrPartSnake[i - 1].rBody)
                    && arrPartSnake[i].rTop.intersect(arrPartSnake[i + 1].rBody)) {
                arrPartSnake[i].bm = (bm_body_top_left)
            } else if (arrPartSnake[i].rRight.intersect(arrPartSnake[i + 1].rBody)
                    && arrPartSnake[i].rTop.intersect(arrPartSnake[i - 1].rBody)
                    || arrPartSnake[i].rRight.intersect(arrPartSnake[i - 1].rBody)
                    && arrPartSnake[i].rTop.intersect(arrPartSnake[i + 1].rBody)) {
                arrPartSnake[i].bm = (bm_body_top_right)
            } else if (arrPartSnake[i].rRight.intersect(arrPartSnake[i + 1].rBody)
                    && arrPartSnake[i].rBottom.intersect(arrPartSnake[i - 1].rBody)
                    || arrPartSnake[i].rRight.intersect(arrPartSnake[i - 1].rBody)
                    && arrPartSnake[i].rBottom.intersect(arrPartSnake[i + 1].rBody)) {
                arrPartSnake[i].bm = (bm_body_bottom_right)
            } else if (arrPartSnake[i].rLeft.intersect(arrPartSnake[i - 1].rBody)
                    && arrPartSnake[i].rRight.intersect(arrPartSnake[i + 1].rBody)
                    || arrPartSnake[i].rLeft.intersect(arrPartSnake[i + 1].rBody)
                    && arrPartSnake[i].rRight.intersect(arrPartSnake[i - 1].rBody)) {
                arrPartSnake[i].bm = (bm_body_horizontal)
            } else if (arrPartSnake[i].rTop.intersect(arrPartSnake[i - 1].rBody)
                    && arrPartSnake[i].rBottom.intersect(arrPartSnake[i + 1].rBody)
                    || arrPartSnake[i].rTop.intersect(arrPartSnake[i + 1].rBody)
                    && arrPartSnake[i].rBottom.intersect(arrPartSnake[i - 1].rBody)) {
                arrPartSnake[i].bm = (bm_body_vertical)
            } else {
                when {
                    move_right || move_left -> arrPartSnake[i].bm = (bm_body_horizontal)
                    move_top || move_bottom -> arrPartSnake[i].bm = (bm_body_vertical)
                }
            }
        }
        if (arrPartSnake[length - 1].rRight.intersect(arrPartSnake[length - 2].rBody)) {
            arrPartSnake[length - 1].bm = (bm_tail_right)
        } else if (arrPartSnake[length - 1].rLeft.intersect(arrPartSnake[length - 2].rBody)) {
            arrPartSnake[length - 1].bm = (bm_tail_left)
        } else if (arrPartSnake[length - 1].rBottom.intersect(arrPartSnake[length - 2].rBody)) {
            arrPartSnake[length - 1].bm = (bm_tail_down)
        } else {
            arrPartSnake[length - 1].bm = (bm_tail_up)
        }
    }

    fun draw(canvas: Canvas) {
        for (i in 0 until length) {
            canvas.drawBitmap(arrPartSnake[i].bm, arrPartSnake[i].x.toFloat(), arrPartSnake[i].y.toFloat(), null)
        }
    }

    fun setFalse() {
        move_left = false
        move_right = false
        move_top = false
        move_bottom = false
    }

}