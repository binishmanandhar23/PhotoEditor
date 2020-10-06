package ja.burhanrashid52.photoeditor;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.NonNull;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

import ja.burhanrashid52.photoeditor.models.StrokeProperties;

/**
 * <p>
 * This class is used to wrap the styles to apply on the TextView on {@link PhotoEditor#addText(String, TextStyleBuilder)} and {@link PhotoEditor#editText(View, String, TextStyleBuilder)}
 * </p>
 *
 * @author <a href="https://github.com/Sulfkain">Christian Caballero</a>
 * @since 14/05/2019
 */
public class TextStyleBuilder {

    private Map<TextStyle, Object> values = new HashMap<>();

    protected Map<TextStyle, Object> getValues() {
        return values;
    }

    /**
     * Set this textSize style
     *
     * @param size Size to apply on text
     */
    public void withTextSize(@NonNull float size) {
        values.put(TextStyle.SIZE, size);
    }

    /**
     * Set this color style
     *
     * @param color Color to apply on text
     */
    public void withTextColor(@NonNull int color) {
        values.put(TextStyle.COLOR, color);
    }

    /**
     * Set this {@link Typeface} style
     *
     * @param textTypeface TypeFace to apply on text
     */
    public void withTextFont(@NonNull Typeface textTypeface) {
        values.put(TextStyle.FONT_FAMILY, textTypeface);
    }

    /**
     * Set this gravity style
     *
     * @param gravity Gravity style to apply on text
     */
    public void withGravity(@NonNull int gravity) {
        values.put(TextStyle.GRAVITY, gravity);
    }

    /**
     * Set this background color
     *
     * @param background Background color to apply on text, this method overrides the preview set on {@link TextStyleBuilder#withBackgroundDrawable(Drawable)}
     */
    public void withBackgroundColor(@NonNull int background) {
        values.put(TextStyle.BACKGROUND, background);
    }

    /**
     * Set this background {@link Drawable}, this method overrides the preview set on {@link TextStyleBuilder#withBackgroundColor(int)}
     *
     * @param bgDrawable Background drawable to apply on text
     */
    public void withBackgroundDrawable(@NonNull Drawable bgDrawable) {
        values.put(TextStyle.BACKGROUND, bgDrawable);
    }

    /**
     * Set this textAppearance style
     *
     * @param textAppearance Text style to apply on text
     */
    public void withTextAppearance(@NonNull int textAppearance) {
        values.put(TextStyle.TEXT_APPEARANCE, textAppearance);
    }

    public void withStrokeWidthColor(@NonNull StrokeProperties strokeWidthColor) {
        values.put(TextStyle.STROKE_WIDTH_COLOR, strokeWidthColor);
    }

    public void withInnerShadow(@NonNull StrokeProperties strokeInnerShadow) {
        values.put(TextStyle.STROKE_INNER_SHADOW, strokeInnerShadow);
    }

    public void withOuterShadow(@NonNull StrokeProperties strokeOuterShadow) {
        values.put(TextStyle.STROKE_OUTER_SHADOW, strokeOuterShadow);
    }

    public void withTextAlign(@NonNull int textAlign){
        values.put(TextStyle.TEXT_ALIGN, textAlign);
    }

    public void withTextJustify(@NonNull int textJustify){
        values.put(TextStyle.TEXT_JUSTIFY, textJustify);
    }

    /**
     * Method to apply all the style setup on this Builder}
     *
     * @param textView TextView to apply the style
     */
    void applyStyle(@NonNull MagicTextView textView) {
        for (Map.Entry<TextStyle, Object> entry : values.entrySet()) {
            switch (entry.getKey()) {
                case SIZE: {
                    final float size = (float) entry.getValue();
                    applyTextSize(textView, size);
                }
                break;

                case COLOR: {
                    final int color = (int) entry.getValue();
                    applyTextColor(textView, color);
                }
                break;

                case FONT_FAMILY: {
                    final Typeface typeface = (Typeface) entry.getValue();
                    applyFontFamily(textView, typeface);
                }
                break;

                case GRAVITY: {
                    final int gravity = (int) entry.getValue();
                    applyGravity(textView, gravity);
                }
                break;

                case BACKGROUND: {
                    if (entry.getValue() instanceof Drawable) {
                        final Drawable bg = (Drawable) entry.getValue();
                        applyBackgroundDrawable(textView, bg);

                    } else if (entry.getValue() instanceof Integer) {
                        final int color = (Integer) entry.getValue();
                        applyBackgroundColor(textView, color);
                    }
                }
                break;

                case TEXT_APPEARANCE: {
                    if (entry.getValue() instanceof Integer) {
                        final int styleAppearance = (Integer) entry.getValue();
                        applyTextAppearance(textView, styleAppearance);
                    }
                }
                break;

                case STROKE_WIDTH_COLOR: {
                    if (entry.getValue() instanceof StrokeProperties) {
                        final StrokeProperties strokeWidthColor = (StrokeProperties) entry.getValue();
                        applyStokeWidth(textView, strokeWidthColor);
                    }
                }
                break;
                case TEXT_ALIGN: {
                    if (entry.getValue() instanceof Integer) {
                        final int textAlign = (Integer) entry.getValue();
                        applyTextAlign(textView, textAlign);
                    }
                }
                break;
                case TEXT_JUSTIFY: {
                    if (entry.getValue() instanceof Integer) {
                        final int textJustify = (Integer) entry.getValue();
                        applyTextJustify(textView, textJustify);
                    }
                }
                break;
            }
        }
    }

    protected void applyTextSize(MagicTextView textView, float size) {
        textView.setTextSize(size);
    }

    protected void applyTextColor(MagicTextView textView, int color) {
        textView.setTextColor(color);
    }

    protected void applyFontFamily(MagicTextView textView, Typeface typeface) {
        textView.setTypeface(typeface);
    }

    protected void applyGravity(MagicTextView textView, int gravity) {
        textView.setGravity(gravity);
    }

    protected void applyBackgroundColor(MagicTextView textView, int color) {
        textView.setBackgroundColor(color);
    }

    protected void applyBackgroundDrawable(MagicTextView textView, Drawable bg) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackground(bg);
        } else {
            textView.setBackgroundDrawable(bg);
        }
    }

    protected void applyTextAppearance(MagicTextView textView, int styleAppearance) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textView.setTextAppearance(styleAppearance);
        } else {
            textView.setTextAppearance(textView.getContext(), styleAppearance);
        }
    }

    protected void applyStokeWidth(MagicTextView textView, StrokeProperties strokeWidthColor) {
        textView.setStroke(strokeWidthColor.getWidth(), strokeWidthColor.getColor());
    }

    protected void applyInnerShadow(MagicTextView textView, StrokeProperties strokeInnerShadow) {
        StrokeProperties.Shadow shadow = strokeInnerShadow.getInnerShadow();
        textView.addInnerShadow(shadow.getR(), shadow.getDx(), shadow.getDy(), shadow.getColor());
    }

    protected void applyOuterShadow(MagicTextView textView, StrokeProperties strokeOuterShadow) {
        StrokeProperties.Shadow shadow = strokeOuterShadow.getOuterShadow();
        textView.addOuterShadow(shadow.getR(), shadow.getDx(), shadow.getDy(), shadow.getColor());
    }

    protected void applyTextAlign(MagicTextView textView, int textAlign) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            textView.setJustificationMode(textView.getJustificationMode());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            textView.setTextAlignment(textAlign);
    }

    protected void applyTextJustify(MagicTextView textView, int justification) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            textView.setJustificationMode(justification);
    }


    /**
     * Enum to maintain current supported style properties used on on {@link PhotoEditor#addText(String, TextStyleBuilder)} and {@link PhotoEditor#editText(View, String, TextStyleBuilder)}
     */
    protected enum TextStyle {
        SIZE("TextSize"),
        COLOR("TextColor"),
        GRAVITY("Gravity"),
        FONT_FAMILY("FontFamily"),
        BACKGROUND("Background"),
        TEXT_APPEARANCE("TextAppearance"),
        STROKE_WIDTH_COLOR("StrokeWidthColor"),
        STROKE_INNER_SHADOW("StrokeInnerShadow"),
        STROKE_OUTER_SHADOW("StrokeOuterShadow"),
        TEXT_ALIGN("TextAlign"),
        TEXT_JUSTIFY("TextJustify");

        TextStyle(String property) {
            this.property = property;
        }

        private String property;

        public String getProperty() {
            return property;
        }
    }
}