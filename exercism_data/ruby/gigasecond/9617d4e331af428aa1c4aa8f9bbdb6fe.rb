class Gigasecond
  def self.from(date)
    gigasecond = 10**9
    days = gigasecond / (60 * 60 * 24)
    date + days
  end
end
