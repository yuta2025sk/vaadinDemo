package com.example.vaadinDemo;

import java.util.Set;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("")
public class MainView extends VerticalLayout {

    private String selectedName;
    private Set<String> selectedDays;

    public MainView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        // Lighten the background
        getStyle().set("background", "linear-gradient(135deg, #e0e7ef 0%, #bfc9d9 100%)");
        showNameSelection();
    }

    private void showNameSelection() {
        removeAll();

        H1 title = new H1("ゆげデザインラボ シフト管理");
        title.getStyle()
                .set("color", "#232526")
                .set("font-size", "3rem")
                .set("margin-bottom", "2rem")
                .set("letter-spacing", "0.1em");

        VerticalLayout panel = new VerticalLayout();
        panel.setPadding(true);
        panel.setSpacing(true);
        panel.setAlignItems(Alignment.CENTER);
        panel.getStyle()
                .set("background", "rgba(255,255,255,0.85)")
                .set("border-radius", "16px")
                .set("box-shadow", "0 4px 24px rgba(0,0,0,0.08)")
                .set("padding", "2rem")
                .set("width", "350px");

        RadioButtonGroup<String> nameGroup = new RadioButtonGroup<>();
        nameGroup.setLabel("自分の名前を選択してください");
        nameGroup.setItems("jun", "yuta", "ryota");
        nameGroup.getStyle().set("color", "#232526");
        nameGroup.addClassName(LumoUtility.FontWeight.BOLD);

        Button nextButton = new Button("次へ", event -> {
            if (nameGroup.getValue() != null) {
                selectedName = nameGroup.getValue();
                showDaySelection();
            }
        });
        nextButton.getStyle()
                .set("background", "#00c6ff")
                .set("color", "#fff")
                .set("font-size", "1.2rem")
                .set("border-radius", "8px");

        panel.add(nameGroup, nextButton);
        add(title, panel);
    }

    private void showDaySelection() {
        removeAll();

        Label nameLabel = new Label("こんにちは、" + selectedName + "さん");
        nameLabel.getStyle()
                .set("color", "#232526")
                .set("font-size", "1.5rem")
                .set("margin-bottom", "1rem");

        VerticalLayout panel = new VerticalLayout();
        panel.setPadding(true);
        panel.setSpacing(true);
        panel.setAlignItems(Alignment.CENTER);
        panel.getStyle()
                .set("background", "rgba(255,255,255,0.85)")
                .set("border-radius", "16px")
                .set("box-shadow", "0 4px 24px rgba(0,0,0,0.08)")
                .set("padding", "2rem")
                .set("width", "350px");

        CheckboxGroup<String> dayGroup = new CheckboxGroup<>();
        dayGroup.setLabel("勤務する曜日を選択してください");
        dayGroup.setItems("月曜日", "火曜日", "水曜日", "木曜日", "金曜日");
        dayGroup.getStyle().set("color", "#232526");

        Button confirmButton = new Button("確定", event -> {
            if (dayGroup.getSelectedItems() != null && !dayGroup.getSelectedItems().isEmpty()) {
                selectedDays = dayGroup.getSelectedItems();
                showSummary();
            }
        });
        confirmButton.getStyle()
                .set("background", "#00c6ff")
                .set("color", "#fff")
                .set("font-size", "1.2rem")
                .set("border-radius", "8px");

        panel.add(dayGroup, confirmButton);
        add(nameLabel, panel);
    }

    private void showSummary() {
        removeAll();

        H1 summaryTitle = new H1("シフト登録完了");
        summaryTitle.getStyle()
                .set("color", "#232526")
                .set("font-size", "2.5rem")
                .set("margin-bottom", "1.5rem");

        Label name = new Label("名前: " + selectedName);
        name.getStyle().set("color", "#232526").set("font-size", "1.2rem");

        Label days = new Label("勤務曜日: " + String.join(", ", selectedDays));
        days.getStyle().set("color", "#232526").set("font-size", "1.2rem");

        add(summaryTitle, name, days);
    }
}
