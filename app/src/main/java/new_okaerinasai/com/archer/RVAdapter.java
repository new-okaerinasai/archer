package new_okaerinasai.com.archer;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.LinearLayoutManager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import  android.widget.ImageView;
import java.util.List;


import  android.widget.TextView;
import  android.support.v7.widget.CardView;
import  android.widget.AdapterView.OnItemClickListener;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {

    List<Card> cards;
    OnItemClickListener mItemClickListener;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Card item);
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;

        CardViewHolder(CardView cv) {
            super(cv);
            cardView = cv;
        }

        public void bind(final Card card, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(card);
                }
            });
        }
    }

    RVAdapter(List cards, OnItemClickListener listener) {
        this.cards = cards;
        this.listener = listener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        CardViewHolder holder = new CardViewHolder(cv);
        return holder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int position) {
        CardView cardView = cardViewHolder.cardView;
        TextView name = (TextView) cardView.findViewById(R.id.name);
        name.setText(cards.get(position).getname());
        cardViewHolder.bind(cards.get(position), listener);
    };




    @Override
    public int getItemCount() {
        return cards.size();
    }
}