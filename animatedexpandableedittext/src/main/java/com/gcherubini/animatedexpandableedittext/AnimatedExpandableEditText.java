package com.gcherubini.animatedexpandableedittext;


import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class AnimatedExpandableEditText extends android.support.v7.widget.AppCompatEditText {

    public static int expandHeightPixels;
    public static int expandAnimationDurationMilliseconds;

    public AnimatedExpandableEditText(Context context) {
        super(context);
        init();
    }

    public AnimatedExpandableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimatedExpandableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        expandAnimationDurationMilliseconds = getResources().getInteger(R.integer.animated_expanded_edit_text_animation_duration_milliseconds);
        expandHeightPixels = getResources().getDimensionPixelSize(R.dimen.animated_expandable_edit_text_expanded_height);

        setOnFocusChangeListener(getOnFocusChangeListener(this));
        setOnEditorActionListener(getOnEditorActionListener(this));
    }

    @NonNull
    private TextView.OnEditorActionListener getOnEditorActionListener(final EditText editText) {
        return new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    editText.clearFocus();
                    if(editText.getContext() != null) {
                        closeKeyboard(v, editText);
                    }
                    return true;
                }
                return false;
            }
        };
    }

    private void closeKeyboard(TextView v, EditText editText) {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @NonNull
    private View.OnFocusChangeListener getOnFocusChangeListener(final EditText editText) {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    expandViewWithAnimation(editText, expandHeightPixels);
                } else {
                    compactViewWithAnimation(editText, expandHeightPixels);
                }
            }
        };
    }

    private void expandViewWithAnimation(final EditText editText, int pixelsToExpand) {
        ValueAnimator animation = ValueAnimator.ofInt((int) editText.getHeight(), (int) editText.getHeight() + pixelsToExpand);
        animateEditTextSize(editText, animation);
    }

    private void compactViewWithAnimation(final EditText editText, int pixelsToExpand) {
        ValueAnimator animation = ValueAnimator.ofInt((int) editText.getHeight(), (int) editText.getHeight() - pixelsToExpand);
        animateEditTextSize(editText, animation);
    }

    private void animateEditTextSize(final EditText editText, ValueAnimator animation) {
        animation.setDuration(expandAnimationDurationMilliseconds);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                editText.getLayoutParams().height = value.intValue();
                editText.requestLayout();
            }
        });
        animation.start();
    }
}
