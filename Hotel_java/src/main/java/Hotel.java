public interface Hotel {
    void setCustomerType(String type);
    int getRating();
    void setWeekRate(double weekRate);
    void setWeekendRate(double weekendRate);
    double getWeekRate();
    double getWeekendRate();
    void setRating(int rating);
    String toString();
}