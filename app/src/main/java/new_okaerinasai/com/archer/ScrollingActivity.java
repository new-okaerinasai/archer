package new_okaerinasai.com.archer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;



import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.LinearLayoutManager;

import android.view.View;
import android.widget.ArrayAdapter;


import java.util.List;
import android.widget.ImageButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import android.widget.ListView;
import android.content.Context;
import  java.util.ArrayList;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;


import android.view.LayoutInflater;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
public class ScrollingActivity extends AppCompatActivity {
    public List<Card> cards;
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        rv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        initializeData();

        initializeAdapter();
    }



    private void initializeData() {
        cards = new ArrayList<Card>();
        cards.add(new Card("Cute anime girl"));
        cards.add(new Card("Clueless testing lesson"));
        cards.add(new Card("Da Vinci's last painting"));
        cards.add(new Card("Lesson 4"));
        cards.add(new Card("Lesson 5"));
    }

    private void initializeAdapter() {
        RVAdapter adapter = new RVAdapter(cards, new RVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Card item) {
                if (item.getname() == "Cute anime girl") {
                    Intent intent = new Intent(ScrollingActivity.this, DrawFromFingerActivity.class);
                    startActivity(intent);
                }
            }
        });
        rv.setAdapter(adapter);
    }
    void showSnackbar(int position) {
        Snackbar.make(rv, "Карточка с номером "+position, Snackbar.LENGTH_LONG).show();
    }

    private void itemClick(int position) {

        //action on item click
    }

}
