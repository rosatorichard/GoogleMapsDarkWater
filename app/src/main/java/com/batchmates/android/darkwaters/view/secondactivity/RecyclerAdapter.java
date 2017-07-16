package com.batchmates.android.darkwaters.view.secondactivity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.batchmates.android.darkwaters.R;
import com.batchmates.android.darkwaters.model.ClosePlaces;
import com.batchmates.android.darkwaters.view.thirdactivity.DisplayActivity;
import com.google.android.gms.maps.GoogleMap;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Android on 7/14/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private static final String TAG = "Recycler ClickListener";
    List<ClosePlaces> listOfPlaces =new ArrayList<>();
    private GoogleMap mMap;

    public RecyclerAdapter(List<ClosePlaces> listOfPlaces,GoogleMap mMap) {
        this.listOfPlaces = listOfPlaces;
        this.mMap=mMap;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView place,discription;
        public ViewHolder(View itemView) {
            super(itemView);
            place=(TextView)itemView.findViewById(R.id.place);
            discription=(TextView)itemView.findViewById(R.id.discription);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ClosePlaces closePlaces=listOfPlaces.get(position);

        holder.place.setText(closePlaces.getPlaceName());
        holder.discription.setText(closePlaces.getPlaceAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(), DisplayActivity.class);
//                intent.putParcelableArrayListExtra("LIST", (ArrayList<? extends Parcelable>) listOfPlaces);
                intent.putExtra("name", closePlaces.getPlaceName());
                intent.putExtra("address",closePlaces.getPlaceAddress());
                intent.putExtra("price",closePlaces.getPriceLvl());
                intent.putExtra("stars",closePlaces.getStars());
                intent.putExtra("attributes",closePlaces.getPlaceAtributes());
                intent.putExtra("phone",closePlaces.getPhoneNumber());

                view.getContext().startActivity(intent);
                Log.d(TAG, "onClick: "+ holder.place.getText().toString());
            }
        });
        FadeAnimation(holder.itemView);


    }

    public void FadeAnimation(View view)
    {
        AlphaAnimation alpha= new AlphaAnimation(0,1);
        alpha.setDuration(1000);
        view.startAnimation(alpha);
    }


    @Override
    public int getItemCount() {
        return listOfPlaces.size();
    }

}
