class Gigasecond
  SECONDS_IN_MINUTE = 60
  MINUTES_IN_HOUR   = 60
  HOURS_IN_DAY      = 24

  def self.from birthday
    birthday + days_for_gigasecond
  end

  def self.seconds_in_day
    SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY
  end

  def self.days_for_gigasecond
    (10**9) / seconds_in_day
  end
end
