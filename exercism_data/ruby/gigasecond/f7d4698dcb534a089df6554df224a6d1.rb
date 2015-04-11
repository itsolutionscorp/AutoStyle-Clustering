class Gigasecond
  def self.from(date)
    date + 10**9 / 86400 # seconds per day
  end
end
