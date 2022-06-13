package com.example.application.views;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@PageTitle("Calculatrice")
@Route(value = "calcul", layout = MainLayout.class)
@Tag("calculator-view")
@JsModule("./views/calculator-view.ts")
public class CalculatorView extends LitTemplate {

    double value1 = 0, value2 = 0, result = 0;
    char operator;

    @Id
    private Button chf0;
    @Id
    private Button chf1;
    @Id
    private Button chf2;
    @Id
    private Button chf3;
    @Id
    private Button chf4;
    @Id
    private Button chf5;
    @Id
    private Button chf6;
    @Id
    private Button chf7;
    @Id
    private Button chf8;
    @Id
    private Button chf9;
    @Id
    private Label ecran;
    @Id
    private HorizontalLayout extendedLayout;
    @Id
    private Checkbox extendedChk;
    @Id
    private Button inverse;
    @Id
    private Button clear;
    @Id("mul")
    private Button mul;
    @Id("equal")
    private Button equal;
    @Id("plus")
    private Button plus;
    @Id("minus")
    private Button minus;
    @Id("div")
    private Button div;
    @Id("tan")
    private Button tan;
    @Id("sin")
    private Button sin;
    @Id("cos")
    private Button cos;

    public CalculatorView() {
        ecran.setText("0");
        List<Button> chiffres = List.of(chf0,chf1,chf2,chf3,chf4,chf5,chf6,chf7,chf8,chf9);

        for (int i = 0; i < chiffres.size(); i++)
            prepare(chiffres,i);

        clear.addClickListener(this::ecranClear);
        plus.addClickListener(this::plusOperation);
        minus.addClickListener(this::minusOperation);
        mul.addClickListener(this::mulOperation);
        div.addClickListener(this::divOperation);
        equal.addClickListener(this::equalOperation);
        extendedChk.addValueChangeListener(this::onCheckChanged);
        inverse.addClickListener(this::onInverse);
        cos.addClickListener(this::cos);
        sin.addClickListener(this::sin);
        tan.addClickListener(this::tan);
    }

    private void cos(ClickEvent<Button> e) {
        value1 = Double.parseDouble(ecran.getText());
        result = Math.cos(value1);
        ecran.setText(String.valueOf(result));
    }

    private void sin(ClickEvent<Button> e) {
        value1 = Double.parseDouble(ecran.getText());
        result = Math.sin(value1);
        ecran.setText(String.valueOf(result));
    }

    private void tan(ClickEvent<Button> e) {
        value1 = Double.parseDouble(ecran.getText());
        result = Math.tan(value1);
        ecran.setText(String.valueOf(result));
    }

    private void equalOperation(ClickEvent<Button> e) {
        value2 = Double.parseDouble(ecran.getText());
        switch (operator){
            case '+':
                result = value1 + value2;
                break;
            case '-':
                result = value1 - value2;
                break;
            case '*':
                result = value1 * value2;
                break;
            case '/':
                if(value2 != 0)
                    result = value1 / value2;
                else{
                    value1 = 0;
                    Notification.show("Impossible de diviser sur 0!");
                }

                break;
        }
        ecran.setText(String.valueOf(result));
    }

    private void divOperation(ClickEvent<Button> e) {
        value1 = Double.parseDouble(ecran.getText());
        operator = '/';
        ecran.setText( "0" );
    }

    private void mulOperation(ClickEvent<Button> e) {
        value1 = Double.parseDouble(ecran.getText());
        operator = '*';
        ecran.setText( "0" );
    }

    private void minusOperation(ClickEvent<Button> e) {
        value1 = Double.parseDouble(ecran.getText());
        operator = '-';
        ecran.setText( "0" );
    }

    private void plusOperation(ClickEvent<Button> e) {
        value1 = Double.parseDouble(ecran.getText());
        operator = '+';
        ecran.setText("0");
    }

    private void ecranClear(ClickEvent<Button> e) {
        ecran.setText("0");
    }

    private void onInverse(ClickEvent<Button> e) {
        value1 = Double.parseDouble(ecran.getText());
        if (value1 == 0)
            Notification.show("Erreur !");
        else {
            value2 = 1/value1;
            ecran.setText(String.valueOf(value2));
        }
    }

    private void onCheckChanged(AbstractField.ComponentValueChangeEvent<Checkbox, Boolean> e) {
        extendedLayout.setVisible(e.getValue());
    }

    private void prepare(List<Button> chiffres, Integer i) {
        Button btn = chiffres.get(i);
        btn.setText(i.toString());
        btn.addClickListener(this::addChiffre);
    }

    private void addChiffre(ClickEvent<Button> e) {
        String number = ecran.getText();
        if (Double.parseDouble(number) != 0)
            ecran.setText(number+e.getSource().getText());
        else
            ecran.setText(e.getSource().getText());
    }
}
