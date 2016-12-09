import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2016/11/1 0001.
 */
public class TestGregorianCalendar {
    public static void main(String[] args) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(now));
        gregorianCalendar.add(5,-1);
        System.out.println(sdf.format(gregorianCalendar.getTime()));
    }
}
