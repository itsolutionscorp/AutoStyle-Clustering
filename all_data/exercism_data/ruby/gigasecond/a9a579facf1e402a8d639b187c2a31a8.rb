class Gigasecond
  def self.from(date)
    date += to_days
  end
  def self.to_days
    10**9 / (60 * 60 * 24)
  end
end
