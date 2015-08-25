public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }

    public String nextDate(){
        if(dayOfMonth()==monthLengths[month()]){
            if(month()==12){
                return "" + 1 + "/" + 1 + "/" + (year()+1);
            }else{
                return "" + 1 + "/" + (month()+1) + "/" + year();
            }
        }
        return "" + (dayOfMonth()+1) + "/" + month() + "/" + year();
    }

}
