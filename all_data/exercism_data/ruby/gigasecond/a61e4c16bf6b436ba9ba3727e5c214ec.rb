class Gigasecond
  def self.from(date)
    time = date.to_time() + (10**9)
    Date.parse(time.to_s)
  end
end
