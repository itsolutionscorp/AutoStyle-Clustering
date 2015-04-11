class Gigasecond
  ONE_GIGASECOND = (10**9)
  SECONDS_PER_MINUTE = 60
  MINUTES_PER_HOUR = 60
  HOURS_PER_DAY = 24
  ONE_GIGASECOND_IN_DAYS = ONE_GIGASECOND /
                           SECONDS_PER_MINUTE /
                           MINUTES_PER_HOUR /
                           HOURS_PER_DAY

  def self.from(date)
    date + ONE_GIGASECOND_IN_DAYS
  end
end
