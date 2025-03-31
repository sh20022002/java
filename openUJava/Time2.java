public class Time2 {
    // Represents the number of seconds passed since midnight
    private int _secFromMid;
    
    // Maximum allowed values for hour, minute, and second
    public static final int MAX_HOUR = 23;
    public static final int MAX_MINUTE = 59;
    public static final int MAX_SECOND = 59;

    /**
     * Constructor for Time2.
     * Initializes the object and chacks if given values are not in norm(0 < hours < 23, 0 < minutes and seconds < 59)
     * if a value is not in norm sets a 0 instead
     */
    public Time2(int h, int m, int s) {
        if (h < 0 || h > MAX_HOUR){
            h = 0;
        }
        if (m < 0 || m > MAX_MINUTE){
            m = 0;
        }
        if (s < 0 || s > MAX_SECOND){
            s = 0;
        }

     
        _secFromMid = h * 3600 + m * 60 + s;
    }    
    // Sets the time from a string in the format "hh:mm:ss".
    public void setTime(String time) {
        String[] parts = time.split(":");
        if (parts.length == 3) {
            this.setHour(Integer.parseInt(parts[0]));
            this.setMinute(Integer.parseInt(parts[1]));
            this.setSecond(Integer.parseInt(parts[2]));
        }
    }
    /**
     * getHour(), getMinute() and getSeconds 
     * @return the hour, minute and seconds part of the time
     */

    public int getHour() {
        return (this.secFromMidnight()/ 3600 );
    }
   
    public int getMinute() {
        return (this.secFromMidnight() % 3600 ) / 60;
    }
   
    public int getSecond() {
        return this.secFromMidnight() % 60;
    }
    /**
     * setHour(), setMinute() and setSeconds 
     * Sets the hours, minutes and seconds values in to the _secFromMid 
     * by hours * 3600 \ minutes * 60 and seconds and if given values are not in norm(0 < hours < 23, 0 < minutes and seconds < 59)
     * uses 0 in the seted value
     */

    public void setHour(int num) {
        if (num < 0 || num > MAX_HOUR){
           num = 0; 
        }
        _secFromMid = num * 3600 + this.getMinute() * 60 + this.getSecond();
    }
    public void setMinute(int num) {
        if (num < 0 || num > MAX_MINUTE){
            num = 0; 
        }
        _secFromMid = this.getHour() * 3600 + num * 60 + this.getSecond();
        
    }
    
    public void setSecond(int num) {
        if (num < 0 || num > MAX_SECOND){
            num = 0; 
        }
        _secFromMid = this.getHour() * 3600 + this.getMinute() * 60 + num;
        
    }

    public String toString() {
        return String.format("%02d:%02d:%02d",this.getHour(), this.getMinute(), this.getSecond());
    }
     /**
     * @return the number of seconds since midnight
     */
    public int secFromMidnight() {
        return _secFromMid;
    }
    /**
     * @return true if times are equal
     */
    public boolean equals(Time2 other) {
        return this.secFromMidnight() == other.secFromMidnight();
    }
     /**
     * @return true if this time is before the other time
     */
    public boolean before(Time2 other) {
        return this.secFromMidnight() < other.secFromMidnight();
    }
    /**
     * @return true if this time is after the other time
     */
    public boolean after(Time2 other) {
        return other.before(this);
    }
     /**
     * @return absolute difference in seconds between this and another time
     */
    public int difference(Time2 other) {
        return Math.abs(this.secFromMidnight() - other.secFromMidnight());
    }
};
