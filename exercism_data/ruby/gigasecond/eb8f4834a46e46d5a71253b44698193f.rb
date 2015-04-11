class Gigasecond
  def self.from(date)
    seconds_in_day = 60 * 60 * 24
    days_in_gigasecond = 1000000000 / seconds_in_day
    date + days_in_gigasecond
  end
end
