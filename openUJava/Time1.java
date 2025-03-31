public class Time1 {
    
    // _hour: private int ,
    // _minute: private int, 
    // _second: private int ,
    // the class has a regular constracter and a constracter that accepts time in a string format
    // and has methods for geting the hour, minute, seconds and checks the time from midnight and more.     
    
    private int _hour;
    private int _minute;
    private int _second;

    
    public Time1(int h, int m, int s) {
        // constracter for Time1 class
        // it validates values in the setter methods
        // and saves thos as attributes
        
        this.setHour(h);
        this.setMinute(m);
        this.setSecond(s);
    }

    public void setTime(String time) {
        // a constracter that accepts time in string format 'hh:mm:ss'

        // splits the string to hours, minutes, seconds by the :
        String[] parts = time.split(":");
        if (parts.length == 3) {
            // checks that hours ,minutes and seconds exist
            // then sets the attributes after parsing them as integers
            this.setHour(Integer.parseInt(parts[0]));
            this.setMinute(Integer.parseInt(parts[1]));
            this.setSecond(Integer.parseInt(parts[2]));
        }
    }

    public int getHour() {
        return _hour;
    }

    public int getMinute() {
        return _minute;
    }

    public int getSecond() {
        return _second;
    }

    public void setHour(int num) {
        // checks if value is 0 < num < 23
        // if not set 0 in hour 
        if (num >= 0 && num <= 23) {
            _hour = num;
        } else {
            _hour = 0;
        }
    }
    public void setMinute(int num) {
        // checks if value is 0 < num < 59
        // if not set 0 in minute 
        if (num >= 0 && num <= 59) {
            _minute = num;
        } else {
            _minute = 0;
        }
    }

    public void setSecond(int num) {
        // checks if value is 0 < num < 59
        // if not set 0 in second 
        if (num >= 0 && num <= 59) {
            _second = num;
        } else {
            _second = 0;
        }
        
    }

    public String toString() {
        // format the time as string 'hh:mm:ss' with two digits each
        return String.format("%02d:%02d:%02d", _hour, _minute, _second);
    }

    public int secFromMidnight() {
        // returns the sconds passed from last midnight
        return _hour * 3600 + _minute * 60 + _second;
    }

    public boolean equals(Time1 other) {
        // returns True if the given time is equals to the class time else returns False
        return _hour == other._hour && _minute == other._minute && _second == other._second;
    }

    public boolean before(Time1 other) {
        // returns True if the given time is before to the class time else returns False
        return this.secFromMidnight() < other.secFromMidnight();
    }

    public boolean after(Time1 other) {
        // returns True if the given time is after to the class time else returns False
        return other.before(this);
    }

    public int difference(Time1 other) {
        // returns the absolute value of the difference between the class time and given time
        return Math.abs(this.secFromMidnight() - other.secFromMidnight());
    }
};
