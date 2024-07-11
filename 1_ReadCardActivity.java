#1_Đọc các thông tin thẻ có gắn chip: lưu thông tin vào storage của phone

package com.example.cardreader;

import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class ReadCardActivity extends Activity {

    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_card);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC không khả dụng trên thiết bị này.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag != null) {
            // Đọc thông tin từ thẻ và lưu trữ
            String cardInfo = readCard(tag);
            saveCardInfo(cardInfo);
        }
    }

    private String readCard(Tag tag) {
        // Logic đọc thông tin thẻ từ tag
        return "Tài khoản: 123456, Tên: John Doe, Ngày mở: 01/01/2020";
    }

    private void saveCardInfo(String cardInfo) {
        // Lưu trữ thông tin thẻ vào storage của phone
        getSharedPreferences("CardInfo", MODE_PRIVATE).edit().putString("card", cardInfo).apply();
    }
}
