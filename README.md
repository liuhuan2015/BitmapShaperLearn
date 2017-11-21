# BitmapShaperLearn
使用BitmapShaper制作圆形图片和圆角图片<br>
在做项目的时候有时总会用到一些带圆角的背景，有时候自己都是用shape图形做的。<br>
但是有时候在做一些图片加载的时候，有时我们会用到带圆角的ImageView，或者圆形ImageView。<br>
遇到这种需求的时候，自己一般是网上搜一下，看有别人写好的圆角或圆形的ImageView，就直接拷贝进项目中用了，也不太清楚它的具体实现。<br>
最近看了一些博客文章，照着http://blog.csdn.net/lmj623565791/article/details/41967509写了一下代码实现。<br>
介绍一下BitmapShader<br>

   BitmapShader是Shader的子类，可以通过Paint.setShader（Shader shader）进行设置.<br>
   这里我们只关注BitmapShader，构造方法：<br>
   mBitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);<br>
    参数1：bitmap<br>
    参数2，参数3：TileMode；<br>
    TileMode的取值有三种：<br>
      CLAMP 拉伸<br>
     REPEAT 重复<br>
     MIRROR 镜像<br>

这篇文章是使用BitmapShader实现的,里面涉及到自定义属性的使用。部分代码：

      /**
     * 初始化BitmapShader
     */
    private void setUpShaper() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        Bitmap bmp = drawableToBitmap(drawable);
        //将bmp作为着色器，在指定区域内绘制bmp
        mBitmapShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        if (type == TYPE_CIRCLE) {
            //拿到bitmap的宽和高的小值
            int bSize = Math.min(bmp.getWidth(), bmp.getHeight());
            scale = mWidth * 1.0f / bSize;
        } else {
            //如果图片的宽或高与View的宽高不匹配，计算出需要缩放的比例
            //缩放的图片的宽高，一定要大于我们的View的宽高，所以我们这里取最大值
            scale = Math.max(getWidth() * 1.0f / bmp.getWidth(),
                    getHeight() * 1.0f / bmp.getHeight());
        }
        //shape的变换矩阵，这里我们主要用于放大或者缩小
        mMatrix.setScale(scale, scale);
        //设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        //设置shaper
        mPaint.setShader(mBitmapShader);
       }
       
-----------------------------------------------------
2017.11.21补充
今天看了主流的图片加载框架Glide，Picasso，,他们是通过Transformation来实现的圆角图片的加载，源码实现中也是通过使用BitmapShader实现的。
Fresco有SimpleDraweeView，支持直接在布局文件中设置圆角属性，但是使用Fresco代码侵入性比较大。

