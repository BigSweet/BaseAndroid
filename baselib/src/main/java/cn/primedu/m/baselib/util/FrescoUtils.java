package cn.primedu.m.baselib.util;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import cn.primedu.m.baselib.MyApplication;
import cn.primedu.m.baselib.R;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/7/13
 */
public class FrescoUtils {

    private static final String PHOTO_FRESCO = "frescocache";

    /**
     *
     * 需要添加依赖:
     *  compile 'jp.wasabeef:fresco-processors:2.0.0'
     *              或者自己拷贝那个类出来
     *
     *
     * 高斯模糊后显示
     * @param url
     * @param draweeView
     * @param width draweeView的宽
     * @param height draweeView的高
     * @param context
     * @param radius  高斯模糊的半径, 每一个像素都取周边(多少个)像素的平均值
     * @param sampling 采样率 原本是设置到BlurPostprocessor上的,因为高斯模糊本身对图片清晰度要求就不高,
     *                 所以此处直接设置到ResizeOptions上,直接让解码生成的bitmap就缩小,而BlurPostprocessor
     *                 内部sampling设置为1,无需再缩
     */

    /**
     * If the image has some ResizeOptions we put also the resized image into the cache with different key.
     * currently don't support downsampling / resizing for GIFs.
     *
     * @param url
     * @param draweeView
     */
    public static void loadUrl(String url, SimpleDraweeView draweeView) {
        load(Uri.parse(url), draweeView);
    }


    public static void load(Uri uri, SimpleDraweeView draweeView) {
        ImageRequest request =
                ImageRequestBuilder.newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)//支持图片渐进式加载
                        .setAutoRotateEnabled(true) //如果图片是侧着,可以自动旋转
                        .build();
        GenericDraweeHierarchyBuilder builder =
                new GenericDraweeHierarchyBuilder(MyApplication.getmContext().getResources());
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(7f);
        roundingParams.setRoundAsCircle(true);
        GenericDraweeHierarchy hierarchy = builder
                .setRoundingParams(roundingParams)
                .setPlaceholderImage(R.drawable.avatar_130)
                .build();
        PipelineDraweeController controller =
                (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(draweeView.getController())
                        .setAutoPlayAnimations(true) //自动播放gif动画
                        .build();
        draweeView.setController(controller);
        draweeView.setHierarchy(hierarchy);
    }


    /**
     * 当设置roundAsCircle为true无效时,采用这个方法,常用在gif的圆形效果上
     * <p>
     * 或者在xml中设置:fresco:roundWithOverlayColor="@color/you_color_id"
     * "you_color_id"是指你的背景色，这样也可以实现圆角、圆圈效果
     * <p>
     * roundAsCircle的局限性:
     * 当使用BITMAP_ONLY（默认）模式时的限制：
     * 并非所有的图片分支部分都可以实现圆角，目前只有占位图片和实际图片可以实现圆角，我们正在努力为背景图片实现圆角功能。
     * 只有BitmapDrawable 和 ColorDrawable类的图片可以实现圆角。我们目前不支持包括NinePatchDrawable和 ShapeDrawable在内的其他类型图片。（无论他们是在XML或是程序中声明的）
     * 动画不能被圆角。
     * 由于Android的BitmapShader的限制，当一个图片不能覆盖全部的View的时候，边缘部分会被重复显示，而非留白。对这种情况可以使用不同的缩放类型
     * （比如centerCrop）来保证图片覆盖了全部的View。 OVERLAY_COLOR模式没有上述限制，但由于这个模式使用在图片上覆盖一个纯色图层的方式来模拟圆角效果，
     * 因此只有在图标背景是静止的并且与图层同色的情况下才能获得较好的效果。
     *
     * @param draweeView
     * @param bgColor    圆形遮罩的颜色,应该与背景色一致
     */
    public static void setCircle(SimpleDraweeView draweeView, int bgColor) {
        RoundingParams roundingParams = RoundingParams.asCircle();//这个方法在某些情况下无法成圆,比如gif
        roundingParams.setOverlayColor(bgColor);//加一层遮罩
        draweeView.getHierarchy().setRoundingParams(roundingParams);
    }


    /**
     * 暂停网络请求
     * 在listview快速滑动时使用
     */
    public static void pause() {
        Fresco.getImagePipeline().pause();
    }


    /**
     * 恢复网络请求
     * 当滑动停止时使用
     */
    public static void resume() {
        Fresco.getImagePipeline().resume();
    }


}
