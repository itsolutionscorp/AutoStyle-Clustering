class Gigasecond
  def self.from(date)
    time = date.to_time + 10**9
    Date.new time.year, time.month, time.day
  end
end
