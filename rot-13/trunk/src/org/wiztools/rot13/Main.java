package org.wiztools.rot13;

import android.app.Activity;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button rot13Button = (Button) findViewById(R.id.rot13button);
        rot13Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				EditText t = (EditText) findViewById(R.id.rot13input);
				String text = t.getText().toString();
				final String ciphered = Rot13.cipher(text);
				t.setText(ciphered);
				
				// Place in clipboard:
				ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				clipboard.setText(ciphered);
			}
		});
    }
}