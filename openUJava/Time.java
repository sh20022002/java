public class Time {
    private int _hour;
    private int _minute;
    private int _second;

    public Time(int h, int m, int s) {
        _hour = h;
        _minute = m;
        _second = s;
    }

    public void setTime(String time) {
        String[] parts = time.split(":");
        if (parts.length == 3) {
            _hour = Integer.parseInt(parts[0]);
            _minute = Integer.parseInt(parts[1]);
            _second = Integer.parseInt(parts[2]);
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
        _hour = num;
    }

    public void setMinute(int num) {
        _minute = num;
    }

    public void setSecond(int num) {
        _second = num;
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", _hour, _minute, _second);
    }

    public int secFromMidnight() {
        return _hour * 3600 + _minute * 60 + _second;
    }

    public boolean equals(Time other) {
        return _hour == other._hour && _minute == other._minute && _second == other._second;
    }

    public boolean before(Time other) {
        return this.secFromMidnight() < other.secFromMidnight();
    }

    public boolean after(Time other) {
        return this.secFromMidnight() > other.secFromMidnight();
    }

    public int difference(Time other) {
        return Math.abs(this.secFromMidnight() - other.secFromMidnight());
    }

    public static void main(String[] args) {
        Time t1 = new Time(12, 30, 45);
        Time t2 = new Time(14, 20, 15);
        System.out.println("t1: " + t1);
        System.out.println("t2: " + t2);
        System.out.println("Difference in seconds: " + t1.difference(t2));
        System.out.println("t1 before t2? " + t1.before(t2));
    }
}
