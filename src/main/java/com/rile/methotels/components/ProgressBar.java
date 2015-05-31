package com.rile.methotels.components;

import java.awt.Color;
import java.text.DecimalFormat;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

/**
 *
 * @author Stefan
 */
public class ProgressBar {
    
    @Property
    @Parameter(value = "null")
    private String width, height;
    @Property
    @Parameter(required = true)
    private double minValue, maxValue, currentValue;
    @Property
    private double percentage;
    @Property
    @Parameter
    private String textFormat;
    private DecimalFormat df = new DecimalFormat("#.##");
    
    @SetupRender
    boolean setupRender() {
        return currentValue >= minValue && currentValue <= maxValue;
    }
    
    @BeginRender
    void beginRender() {
        percentage = ((currentValue - minValue) / (maxValue - minValue)) * 100.0;
    }
    
    public String displayText() {
        String tfLowerCase = textFormat.toLowerCase();
        switch (tfLowerCase) {
            case "both":
                return "" + df.format(percentage) + "% | " + currentValue + "/" + maxValue;
            case "percentage":
                return "" + df.format(percentage) + "%";
            case "number":
            case "numbers":
                return currentValue + "/" + maxValue;
            default:
                return "";
        } 
    }
    
    private String getColorForPercentage() {
        double hue = (percentage / 100.0) * 0.29;
        double saturation = 0.9;
        double brightness = 0.9;
        
        Color hsb = Color.getHSBColor((float) hue, (float) saturation, (float) brightness);
        return String.format("#%02x%02x%02x", hsb.getRed(), hsb.getGreen(), hsb.getBlue());
    }
    
    public String getOuterStyle() {
        return "position: relative; " +
               "background-color: gray; " + 
               "padding: " + (width == null || height == null ? "0;" : "1%;") +
               "width: " + (width == null ? "100%" : width) + ";" +
               "height: " + (height == null ? "100%" : height) + ";";
    }
    
    public String getInnerStyle() {
        return "background-color: " + getColorForPercentage() + ";" + 
               "width: " + percentage + "%; " +
               "height: 100%";
    }

    public String getContentStyle() {
        return "position: absolute; " +
               "left: 50%; " + 
               "top: 50%; " +
               "transform: translate(-50%, -50%);" +
               "text-align: center; " +
               "width: 100%;";
    }
    
}
