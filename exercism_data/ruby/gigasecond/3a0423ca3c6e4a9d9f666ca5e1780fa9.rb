class Gigasecond
  SECONDS_PER_MINUTE = 60
  MINUTES_PER_HOUR = 60
  HOURS_PER_DAY = 24

  def self.from(birthday)
    (as_datetime(birthday) + gigasecond).to_date
  end

  def self.as_datetime(day)
    day.to_datetime.new_offset(0)
  end

  def self.gigasecond
    seconds_to_days(10**9)
  end

  def self.seconds_to_days(seconds)
    seconds / ( SECONDS_PER_MINUTE * MINUTES_PER_HOUR * HOURS_PER_DAY )
  end

end
