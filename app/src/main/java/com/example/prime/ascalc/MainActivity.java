package com.example.prime.ascalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initButtons();
    updateGUI();
  }


  private List<String> tokens = new ArrayList<>();


  private void initButtons() {
    addNumberHandler(R.id.button0, "0");
    addNumberHandler(R.id.button1, "1");
    addNumberHandler(R.id.button2, "2");
    addNumberHandler(R.id.button3, "3");
    addNumberHandler(R.id.button4, "4");
    addNumberHandler(R.id.button5, "5");
    addNumberHandler(R.id.button5, "6");
    addNumberHandler(R.id.button6, "7");
    addNumberHandler(R.id.button8, "8");
    addNumberHandler(R.id.button9, "9");

    addOperationHandler(R.id.buttonPlus, "+");
    addOperationHandler(R.id.buttonMinus, "-");
    addOperationHandler(R.id.buttonMultiply, "*");
    addOperationHandler(R.id.buttonDivide, "/");

    addHandler(R.id.buttonDot, Backend::addDot);
    addHandler(R.id.buttonUndo, Backend::undo);
    addHandler(R.id.buttonFlipSign, Backend::flipSign);
    addHandler(R.id.buttonDot, Backend::addDot);

    findViewById(R.id.buttonEquals).setOnClickListener((v) -> {
      tokens = Collections.singletonList(Backend.computeTokens(tokens));
      updateGUI();
    });

    findViewById(R.id.buttonClear).setOnClickListener((v) -> {
      tokens = Collections.singletonList("0");
      updateGUI();
    });
  }


  private void addHandler(int id, Fun function) {
    findViewById(id).setOnClickListener((v) -> {
      tokens = function.apply(tokens);
      updateGUI();
    });
  }


  private interface Fun {
    List<String> apply(List<String> tokens);
  }


  private void addOperationHandler(int id, String token) {
    findViewById(id).setOnClickListener((v) -> {
      tokens = Backend.addOperationToken(token, tokens);
      updateGUI();
    });
  }


  private void addNumberHandler(int id, String token) {
    findViewById(id).setOnClickListener((v) -> {
      tokens = Backend.addNumberToken(token, tokens);
      updateGUI();
    });
  }


  private void updateGUI() {
    this.<TextView>findViewById(R.id.textViewPrimary).setText(tokens.isEmpty() ? "0" : tokens.get(tokens.size() - 1));
    this.<TextView>findViewById(R.id.textViewSecondary).setText(tokens.isEmpty() ? "[0]" : tokens.toString());
  }
}
