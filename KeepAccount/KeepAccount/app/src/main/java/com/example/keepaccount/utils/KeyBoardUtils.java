package com.example.keepaccount.utils;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.example.keepaccount.R;

public class KeyBoardUtils {

    private KeyboardView keyboardView;
    private EditText editText;
    private final Keyboard k1;// 自定义键盘

    public interface OnEnsureListener {
        public void onEnsure();
    }
    OnEnsureListener onEnsureListener;
    public void setOnEnsureListener(OnEnsureListener onEnsureListener){
        this.onEnsureListener = onEnsureListener;
    }
    //19~25行整个就是一个接口回调

    public KeyBoardUtils(KeyboardView keyboardView, EditText editText) {
        //setInputType为InputType.TYPE_NULL   不然会弹出系统键盘
        editText.setInputType(InputType.TYPE_NULL);
        k1 = new Keyboard(editText.getContext(), R.xml.key);
        this.keyboardView = keyboardView;
        this.editText = editText;
        this.keyboardView.setOnKeyboardActionListener(listener);
        this.keyboardView.setKeyboard(k1);
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(false);
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {

        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = editText.getText();
            int start = editText.getSelectionStart();
            switch (primaryCode) {
                case Keyboard.KEYCODE_DELETE://删除
                    if (editable != null && editable.length() > 0) {
                        if (start > 0) {
                            editable.delete(start - 1, start);
                        }
                    }
                    break;
                case Keyboard.KEYCODE_CANCEL://清零
                    //keyboardView.setVisibility(View.GONE);
                    editable.clear();;
                    break;
                case Keyboard.KEYCODE_DONE://完成
                    onEnsureListener.onEnsure();//通过接口回调的方法，当点击确定时，可以回调这个方法
                    break;
                default://其他数字的插入
                    editable.insert(start, Character.toString((char) primaryCode));
                    break;
            }
        }
    };

    // Activity中获取焦点时调用，显示出键盘
    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    // 隐藏键盘
    public void hideKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE|| visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.GONE);
        }
    }
}