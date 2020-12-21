package com.wjxls.statudyrecyclerviewapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;

public class BackgroundRecyclerView extends RecyclerView {
    private int bitmapWidth; // 图片的总宽度
    private int bitmapHeight; // 图片的总高度
    private Rect dstRect; // 图片位置Rect;
    private Rect srcRect; // 图片展示Rect
    private int scrollY; // RecyclerView的滚动距离
    private float ratio; // 图片缩放比
    private float drawHeight; // 需要绘制的高度
    private Bitmap bitmap; // 图片Bitmap
    private Paint paint; // 绘制图片的画笔

    public BackgroundRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public BackgroundRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BackgroundRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 设置RecyclerView滚动监听
        this.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollY += dy;
            }
        });

        try {
            InputStream stream = getResources().getAssets().open("icon_test.jpg");
            bitmap = BitmapFactory.decodeStream(stream);
            bitmapWidth = bitmap.getWidth();
            bitmapHeight = bitmap.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
        srcRect = new Rect();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    private int phoneWidth = 0;

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        int width = MeasureSpec.getSize(widthSpec);
        int height = MeasureSpec.getSize(heightSpec);
        this.phoneWidth = width;
        dstRect = new Rect(0, 0, width, height);
        // 因为背景图片要铺满背景，因此需要一个缩放比例
        ratio = width / (float) bitmapWidth;
        // 根据缩放比计算实际要draw的高度
        drawHeight = bitmapHeight * ratio;
    }


    /**
     * @date 创建时间:2020/12/21 0021
     * @auther gaoxiaoxiong
     * @Descriptiion 方法一 我的新代码
     **/
  /*  @Override
    public void onDraw(Canvas canvas) {
        if (bitmap != null && scrollY < drawHeight) {
            srcRect.left = 0;
            srcRect.top = 0;
            srcRect.right = bitmapWidth;
            srcRect.bottom = bitmapHeight;
            dstRect.left = 0;
            dstRect.top = (int) (-scrollY);
            dstRect.right = phoneWidth;
            dstRect.bottom = (int) ((drawHeight - scrollY));

            canvas.drawBitmap(bitmap, srcRect, dstRect, paint);
        }
        super.onDraw(canvas);
    }*/


    /**
     * @date 创建时间:2020/12/21 0021
     * @auther gaoxiaoxiong
     * @Descriptiion  方法二 原来作者代码
     **/
   /* @Override
    public void onDraw(Canvas canvas) {
        if (bitmap != null && scrollY < drawHeight) {
            srcRect.left = 0;
            srcRect.top = (int)(scrollY / ratio);
            srcRect.right = bitmapWidth;
            srcRect.bottom = (int)((canvas.getHeight() + scrollY) / ratio);
            canvas.drawBitmap(bitmap, srcRect, dstRect, paint);
            paint.setColor(getContext().getResources().getColor(R.color.red_fa533d));
            paint.setStyle(Paint.Style.FILL);
            Log.e("TAG","srcRect = " + srcRect.toString() +"，dstRect = " +dstRect.toString());
            canvas.drawRect(srcRect,paint);
        }
        super.onDraw(canvas);
    }*/



   /* @Override
    public void onDraw(Canvas canvas) {
        if (bitmap != null && scrollY < drawHeight) {
            srcRect.left = 0;
            srcRect.top = 0;
            srcRect.right = 100;
            srcRect.bottom = 100;

            dstRect.left = 0;
            dstRect.top = 0;
            dstRect.right = 1000;
            dstRect.bottom = 1000;



            paint.setColor(getContext().getResources().getColor(R.color.red_fa533d));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(dstRect,paint);

            canvas.drawBitmap(bitmap, srcRect, dstRect, paint);
        }
        super.onDraw(canvas);
    }*/


  /*    @Override
    public void onDraw(Canvas canvas) {
        if (bitmap != null && scrollY < drawHeight) {
            srcRect.left = 0;
            srcRect.top = 0;
            srcRect.right = 900;
            srcRect.bottom = 900;

            dstRect.left = 0;
            dstRect.top = 0;
            dstRect.right = 1000;
            dstRect.bottom = 1000;
            canvas.drawBitmap(bitmap, srcRect, dstRect, paint);


            dstRect.bottom = 200;
            paint.setColor(getContext().getResources().getColor(R.color.red_fa533d));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(dstRect,paint);


            srcRect.bottom = 100;
            paint.setColor(getContext().getResources().getColor(R.color.purple_500));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(srcRect,paint);


            paint.setColor(getContext().getResources().getColor(R.color.teal_200));
            paint.setStyle(Paint.Style.FILL);
            Rect sourceRect = new Rect(0,0,bitmapWidth,150);
            canvas.drawRect(sourceRect,paint);

        }
        super.onDraw(canvas);
    }*/


   /* @Override
    public void onDraw(Canvas canvas) {
        if (bitmap != null && scrollY < drawHeight) {
            srcRect.left = 0;
            srcRect.top = 0;
            srcRect.right = bitmapWidth;
            srcRect.bottom = bitmapHeight;

            dstRect.left = 0;
            dstRect.top = 0;
            dstRect.right = 1000;
            dstRect.bottom = 1000;
            canvas.drawBitmap(bitmap, srcRect, dstRect, paint);


            dstRect.bottom = 200;
            paint.setColor(getContext().getResources().getColor(R.color.red_fa533d));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(dstRect,paint);


            srcRect.right = bitmapWidth;
            srcRect.bottom = bitmapHeight;
            paint.setColor(getContext().getResources().getColor(R.color.purple_500));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(srcRect,paint);
        }
        super.onDraw(canvas);
    }*/


    @Override
    public void onDraw(Canvas canvas) {
        if (bitmap != null && scrollY < drawHeight) {
            srcRect.right = bitmapWidth;
            srcRect.bottom = bitmapHeight;
            paint.setColor(getContext().getResources().getColor(R.color.purple_500));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(srcRect,paint);


            srcRect.left = 0;
            srcRect.top = 0;
            srcRect.right = bitmapWidth;
            srcRect.bottom = bitmapHeight;

            dstRect.left = 0;
            dstRect.top = 0;
            dstRect.right = 400;
            dstRect.bottom = 400;
            canvas.drawBitmap(bitmap, srcRect, dstRect, paint);


            dstRect.bottom = 200;
            paint.setColor(getContext().getResources().getColor(R.color.red_fa533d));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(dstRect,paint);


        }
        super.onDraw(canvas);
    }
}
