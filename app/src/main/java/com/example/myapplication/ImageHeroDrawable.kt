package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint


class ImageHeroDrawable {
    private val paintText = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintBlue = Paint(Paint.ANTI_ALIAS_FLAG)
    init {
        paintText.color = Color.BLUE
        paintText.textSize = 20F;

        paintBlue.color = Color.BLUE
    }

    fun create(mutableBitmap: Bitmap, hero: Heroes): Bitmap {
//        val workingBitmap = BitmapFactory.decodeResource(resources, R.drawable.hero_test2)
//        val mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true)
//        val canvas = Canvas(mutableBitmap)
//        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
//        paint.color = Color.BLUE
//        paint.textSize = 20F;
//        canvas.drawCircle(50F, 50F, 10F, paint)
//        canvas.drawText("qwe", 150F, 50f, paint)
//        imageView.setImageBitmap(mutableBitmap)

        val copyMutableBitmap: Bitmap = mutableBitmap.copy(mutableBitmap.config, true)
        val canvas = Canvas(copyMutableBitmap)
        canvas.drawCircle(50F, 50F, 10F, paintBlue)
        canvas.drawText(hero.ratingHero.toString(), copyMutableBitmap.width.toFloat()-20F, 50f, paintText)
        canvas.drawText(hero.intelligence.toString(), copyMutableBitmap.width.toFloat()-20F, 70f, paintText)
        canvas.drawText(hero.power.toString(), copyMutableBitmap.width.toFloat()-20F, 90f, paintText)
        canvas.drawText(hero.culture.toString(), copyMutableBitmap.width.toFloat()-20F, 110f, paintText)
        return copyMutableBitmap
    }
}