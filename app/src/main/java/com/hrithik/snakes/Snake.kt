package com.hrithik.snakes

import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log

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
        arrPartSnake.add(PartSnake(bm_head_right, x, y))
        for (i in 1 until length - 1)
            arrPartSnake.add(PartSnake(bm_body_horizontal, arrPartSnake[i - 1].x - GameView.sizeOfMap, y))
        arrPartSnake.add(PartSnake(bm_tail_right, arrPartSnake[length - 2].x - GameView.sizeOfMap, arrPartSnake[length - 2].y))
        move_right = true
    }

    fun update() {
        for (i in length - 1 downTo 1) {
            arrPartSnake[i].x = arrPartSnake[i - 1].x
            arrPartSnake[i].y = arrPartSnake[i - 1].y
        }
        when {
            move_right -> {
                arrPartSnake[0].x = arrPartSnake[0].x + GameView.sizeOfMap
                arrPartSnake[0].bm = bm_head_right
            }
            move_left -> {
                arrPartSnake[0].x = arrPartSnake[0].x - GameView.sizeOfMap
                arrPartSnake[0].bm = bm_head_left
            }
            move_top -> {
                arrPartSnake[0].x = arrPartSnake[0].y - GameView.sizeOfMap
                arrPartSnake[0].bm = bm_head_up
            }
            move_bottom -> {
                arrPartSnake[0].x = arrPartSnake[0].y + GameView.sizeOfMap
                arrPartSnake[0].bm = bm_head_down
            }
        }
        for (i in 1 until length - 1) {
            if (arrPartSnake[i].rLeft.intersect(arrPartSnake[i + 1].rBody)
                    && arrPartSnake[i].rBottom.intersect(arrPartSnake[i - 1].rBody)
                    || arrPartSnake[i].rLeft.intersect(arrPartSnake[i - 1].rBody)
                    && arrPartSnake[i].rBottom.intersect(arrPartSnake[i + 1].rBody))
                arrPartSnake[i].bm = bm_body_bottom_left
            else if (arrPartSnake[i].rRight.intersect(arrPartSnake[i + 1].rBody)
                    && arrPartSnake[i].rBottom.intersect(arrPartSnake[i - 1].rBody)
                    || arrPartSnake[i].rRight.intersect(arrPartSnake[i - 1].rBody)
                    && arrPartSnake[i].rBottom.intersect(arrPartSnake[i + 1].rBody))
                arrPartSnake[i].bm = bm_body_bottom_right
            else if (arrPartSnake[i].rLeft.intersect(arrPartSnake[i + 1].rBody)
                    && arrPartSnake[i].rTop.intersect(arrPartSnake[i - 1].rBody)
                    || arrPartSnake[i].rLeft.intersect(arrPartSnake[i - 1].rBody)
                    && arrPartSnake[i].rTop.intersect(arrPartSnake[i + 1].rBody))
                arrPartSnake[i].bm = bm_body_top_left
            else if (arrPartSnake[i].rRight.intersect(arrPartSnake[i + 1].rBody)
                    && arrPartSnake[i].rTop.intersect(arrPartSnake[i - 1].rBody)
                    || arrPartSnake[i].rRight.intersect(arrPartSnake[i - 1].rBody)
                    && arrPartSnake[i].rTop.intersect(arrPartSnake[i + 1].rBody))
                arrPartSnake[i].bm = bm_body_top_right
            else if (arrPartSnake[i].rTop.intersect(arrPartSnake[i + 1].rBody)
                    && arrPartSnake[i].rBottom.intersect(arrPartSnake[i - 1].rBody)
                    || arrPartSnake[i].rTop.intersect(arrPartSnake[i - 1].rBody)
                    && arrPartSnake[i].rBottom.intersect(arrPartSnake[i + 1].rBody))
                arrPartSnake[i].bm = bm_body_vertical
            else if (arrPartSnake[i].rLeft.intersect(arrPartSnake[i + 1].rBody)
                    && arrPartSnake[i].rRight.intersect(arrPartSnake[i - 1].rBody)
                    || arrPartSnake[i].rLeft.intersect(arrPartSnake[i - 1].rBody)
                    && arrPartSnake[i].rRight.intersect(arrPartSnake[i + 1].rBody))
                arrPartSnake[i].bm = bm_body_horizontal
            else {
                if (move_right) {
                    arrPartSnake[i].bm = (bm_body_horizontal)
                } else if (move_bottom) {
                    arrPartSnake[i].bm = (bm_body_vertical)
                } else if (move_top) {
                    arrPartSnake[i].bm = (bm_body_vertical)
                } else {
                    arrPartSnake[i].bm = (bm_body_horizontal)
                }
            }
        }
        when {
            (arrPartSnake[length - 1].rRight.intersect(arrPartSnake[length - 2].rBody)) ->
                arrPartSnake[length - 1].bm = bm_tail_right
            (arrPartSnake[length - 1].rLeft.intersect(arrPartSnake[length - 2].rBody)) ->
                arrPartSnake[length - 1].bm = bm_tail_left
            (arrPartSnake[length - 1].rTop.intersect(arrPartSnake[length - 2].rBody)) ->
                arrPartSnake[length - 1].bm = bm_tail_up
            (arrPartSnake[length - 1].rBottom.intersect(arrPartSnake[length - 2].rBody)) ->
                arrPartSnake[length - 1].bm = bm_tail_down

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
