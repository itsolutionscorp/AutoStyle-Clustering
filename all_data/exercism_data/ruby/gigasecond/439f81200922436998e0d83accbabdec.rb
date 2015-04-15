class Gigasecond
  GIGASECOND = 10**9      #seconds
  SECONDS_PER_DAY = 86400

  def self.from(date)
    gigasecond_anniverssary = date + (GIGASECOND/SECONDS_PER_DAY)
  end
end
