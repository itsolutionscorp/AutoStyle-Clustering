class Gigasecond
  GIGASECOND = 10**9      #seconds
  SECONDS_PER_DAY = 86400

  def self.from(date)
    gigasecond_anniversary = date + (GIGASECOND/SECONDS_PER_DAY)
  end
end
