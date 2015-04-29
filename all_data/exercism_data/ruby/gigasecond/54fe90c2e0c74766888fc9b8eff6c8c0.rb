class Gigasecond
  def self.from(date)
    seconds_per_day = 60 * 60 * 24
    date + (10**9) / seconds_per_day
  end
end
