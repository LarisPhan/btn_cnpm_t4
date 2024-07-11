#2_Quản lý thẻ: chọn thẻ cần tương tác và xóa thẻ khỏi danh sách

package com.example.cardmanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ManageCardsActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> cards;
    private ArrayAdapter<String> adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cards);

        listView = findViewById(R.id.listView);
        sharedPreferences = getSharedPreferences("CardInfo", MODE_PRIVATE);

        loadCards();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cards);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ManageCardsActivity.this, "Thẻ được chọn: " + cards.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                removeCard(position);
                return true;
            }
        });
    }

    private void loadCards() {
        // Lấy thông tin thẻ từ storage
        cards = new ArrayList<>();
        String cardInfo = sharedPreferences.getString("card", "");
        if (!cardInfo.isEmpty()) {
            cards.add(cardInfo);
        }
    }

    private void removeCard(int position) {
        // Xóa thẻ khỏi danh sách và cập nhật storage
        cards.remove(position);
        sharedPreferences.edit().remove("card").apply();
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Đã xóa thẻ.", Toast.LENGTH_SHORT).show();
    }
}
