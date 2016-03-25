package com.bluecup.hongyu.mocku2020.ui.trending;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bluecup.hongyu.mocku2020.R;
import com.bluecup.hongyu.mocku2020.data.api.module.Repository;
import com.bluecup.hongyu.mocku2020.ui.misc.Truss;
import com.bluecup.hongyu.mocku2020.ui.transform.CircleStrokeTransformation;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/24_下午4:07
 */
public final class TrendingItemView extends RelativeLayout {

    @Bind(R.id.trending_repository_avatar)
    ImageView avatorImg;
    @Bind(R.id.trending_repository_name)
    TextView nameTxt;
    @Bind(R.id.trending_repository_description)
    TextView descriptionTxt;
    @Bind(R.id.trending_repository_stars)
    TextView starsTxt;
    @Bind(R.id.trending_repository_forks)
    TextView forksTxt;
    private final int descriptionColor;
    private final CircleStrokeTransformation avatorTransformation;

    public TrendingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.textColorSecondary, typedValue, true);
        descriptionColor = typedValue.data;
        avatorTransformation = new CircleStrokeTransformation(context, ContextCompat.getColor(context, R.color.avatar_stroke), 1);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }


    public void bindTo(Repository repository, Picasso picasso){
        picasso.load(repository.owner.avator_url).placeholder(R.drawable.avatar)
                .fit()
                .transform(avatorTransformation)
                .into(avatorImg);

        nameTxt.setText(repository.name);
        starsTxt.setText(String.valueOf(repository.watchers));
        forksTxt.setText(String.valueOf(repository.forks));

        Truss description = new Truss();

        description.append(repository.owner.login);

        if (!TextUtils.isEmpty(repository.description)) {
            description.pushSpan(new ForegroundColorSpan(descriptionColor));
            description.append("-");
            description.append(repository.description);
            description.popSpan();
        }
        descriptionTxt.setText(description.build());

    }

}

