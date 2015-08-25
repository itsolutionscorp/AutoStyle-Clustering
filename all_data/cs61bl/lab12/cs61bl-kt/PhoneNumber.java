public class PhoneNumber {

    private String number;

    /*
     * Constructor takes a String that represents a phone number. The String
     * should contain numbers only and should be of length 9.
     */
    public PhoneNumber(String num) {
        if (num.length() != 10) {
            throw new IllegalArgumentException("phone number was not 10 numbers");
        }
        this.number = num;
    }

    public void changeNumber(String num){
        if (num.length() != 10) {
            throw new IllegalArgumentException("phone number was not 10 numbers");
        }
        this.number = num;        
    }

    @Override
    public String toString() {
        return number;
    }

    // TODO Add additional methods?

}
