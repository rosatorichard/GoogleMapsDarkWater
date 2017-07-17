package com.batchmates.android.darkwaters.view.thirdactivity;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Android on 7/16/2017.
 */

public class ThirdActivityPresenter implements ThirdActivityContract.Presenter{

    ThirdActivityContract.View view;
    @Override
    public void addview(ThirdActivityContract.View view) {
        this.view=view;
    }

    @Override
    public void removeView() {
        this.view=null;
    }

    @Override
    public void setUpDisplay(ImageView imageView, TextView name, TextView address, TextView phone, TextView price, ImageView stars) {

    }

    @Override
    public void choseStars(ImageView placeStars, float stars) {

        switch((int) stars)
        {
            case 1:
                Glide.with(placeStars.getContext()).load("https://upload.wikimedia.org/wikipedia/commons/d/dd/Star_rating_1_of_5.png").into(placeStars);
                break;

            case 2:
                Glide.with(placeStars.getContext()).load("https://upload.wikimedia.org/wikipedia/commons/9/95/Star_rating_2_of_5.png").into(placeStars);
                break;

            case 3:
                Glide.with(placeStars.getContext()).load("https://upload.wikimedia.org/wikipedia/commons/2/2f/Star_rating_3_of_5.png").into(placeStars);
                break;

            case 4:
                Glide.with(placeStars.getContext()).load("https://upload.wikimedia.org/wikipedia/commons/f/fa/Star_rating_4_of_5.png").into(placeStars);
                break;

            case 5:
                Glide.with(placeStars.getContext()).load("https://www.7boats.com/academy/wp-content/uploads/2017/02/Review-Stars.png").into(placeStars);
                break;

            default:
                Glide.with(placeStars.getContext()).load("https://upload.wikimedia.org/wikipedia/commons/4/4a/Star_rating_0_of_5.png").into(placeStars);
                break;
        }
    }
}
