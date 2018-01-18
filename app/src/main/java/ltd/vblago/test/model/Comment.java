package ltd.vblago.test.model;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Comment {
    public String date;
    public String point;
    public String great;
    public String good;
    public String fine;
    public String bad;
    public String range;
    public String quality;
    public String price;

    public Comment() {
        setDate();
        setPoint();
        this.great = "";
        this.good = "";
        this.fine = "";
        this.bad = "";
        this.range = "";
        this.quality = "";
        this.price = "";
    }

    private void setDate() {
        Calendar calendar = new GregorianCalendar();

        date = String.format(Locale.US,"%02d.%02d.%d",
                        calendar.get(Calendar.DAY_OF_MONTH),
                        (calendar.get(Calendar.MONTH)+1),
                        calendar.get(Calendar.YEAR));
    }

    private void setPoint() {
        this.point = "1";
    }
}
