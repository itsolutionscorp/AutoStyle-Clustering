class Gigasecond
  def self.from(date)
    date + days_in_a_gigasecond
  end

  def self.days_in_a_gigasecond
    gigasecond = 10**9
    seconds_in_a_day = 60 * 60 * 24
    gigasecond / seconds_in_a_day
  end
end
