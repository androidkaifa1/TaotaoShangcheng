package com.taotao.taotaoshangcheng.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;
import com.taotao.taotaoshangcheng.R;
import com.taotao.taotaoshangcheng.bean.ShoppingCart;
import com.taotao.taotaoshangcheng.bean.Wares;
import com.taotao.taotaoshangcheng.utils.CartProvider;
import com.taotao.taotaoshangcheng.utils.ToastUtils;

import java.util.List;


/**
 * Created by <a href="http://www.cniao5.com">菜鸟窝</a>
 * 一个专业的Android开发在线教育平台
 */
public class HWAdatper extends SimpleAdapter<Wares> {

    CartProvider provider ;

    public HWAdatper(Context context, List<Wares> datas) {
        super(context, R.layout.template_hot_wares, datas);
        provider = CartProvider.newInstance(context);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, final Wares wares) {
        SimpleDraweeView draweeView = (SimpleDraweeView) viewHolder.getView(R.id.drawee_view);
        draweeView.setImageURI(Uri.parse(wares.getImgUrl()));

        viewHolder.getTextView(R.id.text_title).setText(wares.getName());

        Button button =viewHolder.getButton(R.id.btn_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                provider.put(convertData(wares));

                ToastUtils.show(context,"已添加到购物车");
            }
        });

        viewHolder.getTextView(R.id.text_price).setText(wares.getPrice().toString());
    }

    public void  resetLayout(int layoutId){


        this.layoutResId  = layoutId;

        notifyItemRangeChanged(0,getDatas().size());


    }



    public ShoppingCart convertData(Wares item){

        ShoppingCart cart = new ShoppingCart();

        cart.setId(item.getId());
        cart.setDescription(item.getDescription());
        cart.setImgUrl(item.getImgUrl());
        cart.setName(item.getName());
        cart.setPrice(item.getPrice());

        return cart;
    }
}
