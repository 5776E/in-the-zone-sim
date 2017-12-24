/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itzfx.fxml.controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author prem
 */
public class Keyboard {

    @FXML
    private AnchorPane root;

    @FXML
    private Group keyGroup;

    private Map<KeyCode, Rectangle> mappings;

    @FXML
    private void initialize() {
        root.setUserData(this);
        List<KeyCode> row1 = Arrays.asList(KeyCode.ESCAPE, KeyCode.F1, KeyCode.F2, KeyCode.F3, KeyCode.F4,
                KeyCode.F5, KeyCode.F6, KeyCode.F7, KeyCode.F8, KeyCode.F9, KeyCode.F10,
                KeyCode.F11, KeyCode.F12, KeyCode.PRINTSCREEN, KeyCode.SCROLL_LOCK, KeyCode.PAUSE);
        List<KeyCode> row2 = Arrays.asList(KeyCode.BACK_QUOTE, KeyCode.DIGIT1, KeyCode.DIGIT2, KeyCode.DIGIT3,
                KeyCode.DIGIT4, KeyCode.DIGIT5, KeyCode.DIGIT6, KeyCode.DIGIT7, KeyCode.DIGIT8,
                KeyCode.DIGIT9, KeyCode.DIGIT0, KeyCode.MINUS, KeyCode.EQUALS, KeyCode.INSERT,
                KeyCode.HOME, KeyCode.PAGE_UP, KeyCode.NUM_LOCK, KeyCode.BACK_SPACE,
                KeyCode.DIVIDE, KeyCode.MULTIPLY, KeyCode.SUBTRACT);
        List<KeyCode> row3 = Arrays.asList(KeyCode.TAB, KeyCode.Q, KeyCode.W, KeyCode.E, KeyCode.R, KeyCode.T,
                KeyCode.Y, KeyCode.U, KeyCode.I, KeyCode.O, KeyCode.P, KeyCode.OPEN_BRACKET,
                KeyCode.CLOSE_BRACKET, KeyCode.BACK_SLASH, KeyCode.DELETE, KeyCode.END,
                KeyCode.PAGE_DOWN, KeyCode.NUMPAD7, KeyCode.NUMPAD8, KeyCode.NUMPAD9,
                KeyCode.PLUS);
        List<KeyCode> row4 = Arrays.asList(KeyCode.CAPS, KeyCode.A, KeyCode.S, KeyCode.D, KeyCode.F, KeyCode.G,
                KeyCode.H, KeyCode.J, KeyCode.K, KeyCode.L, KeyCode.SEMICOLON, KeyCode.QUOTE,
                KeyCode.ENTER, KeyCode.NUMPAD4, KeyCode.NUMPAD5, KeyCode.NUMPAD6);
        List<KeyCode> row5 = Arrays.asList(KeyCode.SHIFT, KeyCode.Z, KeyCode.X, KeyCode.C, KeyCode.V, KeyCode.B,
                KeyCode.N, KeyCode.M, KeyCode.COMMA, KeyCode.PERIOD, KeyCode.SLASH,
                KeyCode.AGAIN, KeyCode.UP, KeyCode.NUMPAD1, KeyCode.NUMPAD2, KeyCode.NUMPAD3,
                KeyCode.COLORED_KEY_1);
        List<KeyCode> row6 = Arrays.asList(KeyCode.CONTROL, KeyCode.META, KeyCode.ALT, KeyCode.SPACE, KeyCode.ROMAN_CHARACTERS,
                KeyCode.JAPANESE_HIRAGANA, KeyCode.ALPHANUMERIC, KeyCode.ALL_CANDIDATES, KeyCode.LEFT,
                KeyCode.DOWN, KeyCode.RIGHT, KeyCode.NUMPAD0, KeyCode.DECIMAL);
        Iterator<KeyCode> it = Arrays.asList(row1, row2, row3, row4, row5, row6).stream().flatMap(r -> r.stream()).collect(Collectors.toList()).iterator();
        mappings = keyGroup.getChildren().stream().sequential().filter(n -> n instanceof Rectangle)
                .map(n -> (Rectangle) n).collect(Collectors.toMap(r -> it.next(), Function.identity()));
        mappings.entrySet().stream().forEach(e -> e.getValue().addEventFilter(MouseEvent.MOUSE_PRESSED, m -> {
            System.out.println(m);
            ((BorderPane) root.getParent()).getLeft().fireEvent(new KeyEvent(null, null, KeyEvent.KEY_PRESSED, null, null, e.getKey(), false, false, false, false));
        }));
    }

    private Rectangle selected;

    public void remove(KeyCode k) {
        mappings.get(k).setFill(null);
    }

    public void selected(KeyCode k) {
        if (selected != null) {
            selected.setFill(null);
        }
        selected = mappings.get(k);
        if (selected != null) {
            selected.setFill(new Color(.5, .7, 1, .25));
        }
    }

    public void deselect() {
        if (selected != null) {
            selected.setFill(null);
            selected = null;
        }
    }

    public void save() {
        if (selected != null) {
            selected.setFill(new Color(1, 1, 1, .25));
            selected = null;
        }
    }
}