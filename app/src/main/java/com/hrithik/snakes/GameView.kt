package com.hrithik.snakes

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class GameView(ctx: Context, attrs: AttributeSet) : View(ctx, attrs) {
    private var bmGrass1: Bitmap
    private var bmGrass2: Bitmap
    private var bmSnake: Bitmap
    private var arrGrass: ArrayList<Grass> = ArrayList()
    private var snake: Snake
    private var move = false
    private var mx = 0.0F
    private var my = 0.0F
    private var r:Runnable

    companion object {
        val sizeOfMap = 75 * Constants.SCREEN_WIDTH / 1080
    }

    init {
        bmGrass1 = BitmapFactory.decodeResource(resources, R.drawable.grass01)
        bmGrass1 = Bitmap.createScaledBitmap(bmGrass1, sizeOfMap, sizeOfMap, true)
        bmGrass2 = BitmapFactory.decodeResource(resources, R.drawable.grass02)
        bmGrass2 = Bitmap.createScaledBitmap(bmGrass2, sizeOfMap, sizeOfMap, true)
        bmSnake = BitmapFactory.decodeResource(resources, R.drawable.snake)
        bmSnake = Bitmap.createScaledBitmap(bmSnake, 14 * sizeOfMap, sizeOfMap, true)
        val h = 21
        val w = 12
        for (i in 0 until h) {
            for (j in 0 until w) {
                if ((j + i) % 2 == 0) {
                    arrGrass.add(Grass(bmGrass1, j * bmGrass1.width + Constants.SCREEN_WIDTH / 2 - w / 2 * bmGrass1.width,
                            i * bmGrass1.height + 50 * Constants.SCREEN_HEIGHT / 1920, bmGrass1.width, bmGrass1.height))
                } else {
                    arrGrass.add(Grass(bmGrass2, j * bmGrass2.width + Constants.SCREEN_WIDTH / 2 - w / 2 * bmGrass2.width,
                            i * bmGrass2.height + 50 * Constants.SCREEN_HEIGHT / 1920, bmGrass2.width, bmGrass2.height))
                }
            }
        }
        
        snake = Snake(bmSnake, arrGrass[126].x, arrGrass[126].y, 4)
        r = Runnable { invalidate() }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                if (!move)
                    move = true
                else {
                    if (mx - event.x > 100 * Constants.SCREEN_WIDTH / 1080 && !snake.move_right) {
                        snake.setFalse()
                        snake.move_left = true
                    } else if (event.x - mx > 100 * Constants.SCREEN_WIDTH / 1080 && !snake.move_left) {
                        snake.setFalse()
                        snake.move_right = true
                    } else if (my - event.y > 100 * Constants.SCREEN_WIDTH / 1080 && !snake.move_bottom) {
                        snake.setFalse()
                        snake.move_top = true
                    } else if (event.y - my > 100 * Constants.SCREEN_WIDTH / 1080 && !snake.move_top) {
                        snake.setFalse()
                        snake.move_bottom = true
                    }
                }
                mx = event.x
                my = event.y
            }
            MotionEvent.ACTION_UP -> {
                mx = 0.0F
                my = 0.0F
                move = false
            }
        }
        return true

        /*val min_distance = 100
        var downX = 0.0f
        var downY = 0.0f
        val upX:Float
        val upY:Float
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                return true
            }
            MotionEvent.ACTION_UP -> {
                upX = event.x
                upY = event.y
                val deltaX: Float = downX - upX
                val deltaY: Float = downY - upY

                //HORIZONTAL SCROLL
                if (abs(deltaX) > abs(deltaY)) {
                    if (abs(deltaX) > min_distance) {
                        // left or right
                        if (deltaX < 0) {
                            snake.setFalse()
                            snake.move_left = true
                            return true
                        }
                        if (deltaX > 0) {
                            snake.setFalse()
                            snake.move_right = true
                            return true
                        }
                    }
                } else {
                    if (abs(deltaY) > min_distance) {
                        // top or down
                        if (deltaY < 0) {
                            snake.setFalse()
                            snake.move_bottom = true
                            return true
                        }
                        if (deltaY > 0) {
                            snake.setFalse()
                            snake.move_top = true
                            return true
                        }
                    }
                }
                return true
            }
        }
        return false*/
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawColor(Color.GREEN)
        for (i in 0 until arrGrass.size)
            canvas.drawBitmap(arrGrass[i].bm, arrGrass[i].x.toFloat(), arrGrass[i].y.toFloat(), null)
        snake.update()
        postDelayed(r, 1000)
        snake.draw(canvas)
    }

}