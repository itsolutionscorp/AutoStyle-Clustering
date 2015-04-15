class Gigasecond
  def self.from(test_date)
    gigasecond = 10**9
    giga_minute = gigasecond/60
    giga_hours = giga_minute/60
    giga_days = giga_hours/24

    result_date = test_date + giga_days

  end
end
