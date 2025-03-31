public class BusArrival {

    // Bus line number, should be between 1 and 99
    private int _lineNumber;

    // Time of arrival using Time1 class
    private Time1 _arrivlTime;

    // Number of passengers on the bus, between 0 and 70
    private int _noOfPassengers;

    /**
     * Constructor with explicit hour, minute, second
     */
    public BusArrival(int lineNum, int pass, int h, int m, int s) {
        this.setLineNum(lineNum);
        this.setNoOfPass(pass);
        _arrivlTime = new Time1(h, m, s);
    }

    /**
     * Constructor with Time1 object
     */
    public BusArrival(int lineNum, int pass, Time1 t) {
        this.setLineNum(lineNum);
        this.setNoOfPass(pass);
        this.setArrivalTime(t);
    }

    /**
     * Copy constructor
     */
    public BusArrival(BusArrival other) {
        this._lineNumber = other._lineNumber;
        this._arrivlTime = other._arrivlTime;
        this._noOfPassengers = other._noOfPassengers;
    }

    /**
     * @return arrival time object
     */
    public Time1 getArrivalTime() {
        return this._arrivlTime;
    }

    /**
     * @return bus line number
     */
    public int getLineNum() {
        return this._lineNumber;
    }

    /**
     * @return number of passengers on the bus
     */
    public int getNoOfPass() {
        return this._noOfPassengers;
    }

    // Private setter for arrival time
    private void setArrivalTime(Time1 t) {
        _arrivlTime = t;
    }

    // Private setter with validation for line number
    private void setLineNum(int num) {
        if (num < 1 || num > 99) {
            _lineNumber = 1;
        } else {
            _lineNumber = num;
        }
    }

    // Private setter with validation for number of passengers
    private void setNoOfPass(int num) {
        if (num < 0 || num > 70) {
            _noOfPassengers = 0;
        } else {
            _noOfPassengers = num;
        }
    }

    /**
     * Compares two BusArrival objects for logical equality
     * @return true if all fields are equal, false otherwise
     */
    public boolean equals(BusArrival other) {
        return this.getLineNum() == other.getLineNum() &&
               this.getNoOfPass() == other.getNoOfPass() &&
               this.getArrivalTime().equals(other.getArrivalTime());
    }

    /**
     * @return String describing the bus arrival
     */
    public String toString() {
        int passengers = this.getNoOfPass();
        String arrival = this.getArrivalTime().toString();
        int lineNum = this.getLineNum();
        return String.format("Buss Line: %02d, Arrives in: %8s with %02d passengers.", lineNum, arrival, passengers);
    }

    /**
     * @return true if this bus has more passengers than the other
     */
    public boolean fuller(BusArrival other) {
        return this.getNoOfPass() > other.getNoOfPass();
    }

    /**
     * @return true if this bus arrives before the other
     */
    public boolean before(BusArrival other) {
        return this._arrivlTime.before(other._arrivlTime);
    }

    /**
     * @return true if the bus is full (70 passengers)
     */
    public boolean isFull() {
        return this.getNoOfPass() == 70;
    }

    /**
     * @return absolute difference in arrival time minutes between this and other bus
     */
    public int elapsedTime(BusArrival other) {
        int arrivalMinute1 = this.getArrivalTime().getMinute();
        int arrivalMinute2 = other.getArrivalTime().getMinute();
        return Math.abs(arrivalMinute1 - arrivalMinute2);
    }
};
