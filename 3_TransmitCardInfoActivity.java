#3_Truyền thông tin qua đầu quét: quét vào đầu thu

package com.example.cardtransmitter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class TransmitCardInfoActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> cards;
    private ArrayAdapter<String> adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transmit_card_info);

        listView = findViewById(R.id.listView);
        sharedPreferences = getSharedPreferences("CardInfo", MODE_PRIVATE);

        loadCards();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cards);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> transmitCardInfo(cards.get(position)));
    }

    private void loadCards() {
        // Lấy thông tin thẻ từ storage
        cards = new ArrayList<>();
        String cardInfo = sharedPreferences.getString("card", "");
        if (!cardInfo.isEmpty()) {
            cards.add(cardInfo);
        }
    }

    private void transmitCardInfo(String cardInfo) {
        // Logic truyền thông tin thẻ qua đầu thu (ví dụ: giao tiếp NFC hoặc Bluetooth)
        Toast.makeText(this, "Truyền thông tin thẻ: " + cardInfo, Toast.LENGTH_SHORT).show();
        // Ví dụ: gửi qua Bluetooth
        // BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // BluetoothDevice device = bluetoothAdapter.getRemoteDevice("device_address");
        // connectAndTransmit(device, cardInfo);
    }
}
