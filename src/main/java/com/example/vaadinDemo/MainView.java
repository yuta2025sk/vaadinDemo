package com.example.vaadinDemo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private Label greetingLabel = new Label();

    public MainView() {
        Button button = new Button("クリックしてね", e -> openSelectionDialog());
        greetingLabel.getStyle().set("font-size", "48px"); // 文字を大きく
        greetingLabel.getStyle().set("text-align", "center"); // テキスト中央揃え
        greetingLabel.getStyle().set("width", "100%"); // 横幅いっぱい
        setSizeFull(); // レイアウトを画面いっぱいに
        setAlignItems(Alignment.CENTER); // 垂直方向中央
        setJustifyContentMode(JustifyContentMode.CENTER); // 水平方向中央
        add(button, greetingLabel);
    }

    private void openSelectionDialog() {
        Dialog dialog = new Dialog();
        RadioButtonGroup<String> group = new RadioButtonGroup<>();
        group.setLabel("時間帯を選択してください");
        group.setItems("朝", "昼", "夜");

        Button okButton = new Button("OK", event -> {
            String selected = group.getValue();
            if (selected != null) {
                Notification.show("選択された時間帯: " + selected);
                switch (selected) {
                    case "朝":
                        greetingLabel.setText("おはよう");
                        break;
                    case "昼":
                        greetingLabel.setText("こんにちは");
                        break;
                    case "夜":
                        greetingLabel.setText("こんばんわ");
                        break;
                }
                dialog.close();
            } else {
                Notification.show("時間帯を選択してください");
            }
        });

        dialog.add(new VerticalLayout(group, okButton));
        dialog.open();
    }
}