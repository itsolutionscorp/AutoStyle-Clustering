class Gigasecond

  def self.from(newdate)
    Gigasecond.gigadays
    @newdate = newdate.to_date + @days_to_add
  end

  def self.gigadays
    seconds_in_a_minute = 60
    minutes_in_an_hour = 60
    hours_in_a_day = 24
    seconds_in_a_day = seconds_in_a_minute * minutes_in_an_hour * hours_in_a_day
    gigasecond = 1_000_000_000
    @days_to_add = gigasecond / seconds_in_a_day
  end
end
