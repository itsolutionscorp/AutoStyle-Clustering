class Gigasecond
  def self.from(date)
    seconds_in_a_day = 86_400
    date + (10**9 / seconds_in_a_day)
  end
end
