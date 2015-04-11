class Gigasecond
  # returns the date + 10^9 seconds
  def self.from date
    date + 10**9 - 3600
  end
end
