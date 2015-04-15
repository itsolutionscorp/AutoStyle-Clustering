class Gigasecond

  def self.from(date)
    time = Time.new(date.year, date.month, date.day, 10, 0, 0) + 10**9 
    return Date.new(time.year, time.month, time.day)
  end

end
