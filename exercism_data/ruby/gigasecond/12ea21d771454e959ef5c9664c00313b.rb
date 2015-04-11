class Gigasecond
  GIGASECOND = 10**9
  SECONDS_IN_A_MINUTE = 60
  MINUTES_IN_AN_HOUR = 60
  HOURS_IN_A_DAY = 24

  def self.from start_date
    start_date + days_in_gigasecond
  end

  def self.days_in_gigasecond
    GIGASECOND / seconds_in_a_day
  end

  def self.seconds_in_a_day
    SECONDS_IN_A_MINUTE * MINUTES_IN_AN_HOUR * HOURS_IN_A_DAY
  end
end
