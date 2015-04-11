class Gigasecond
  def self.from(day)
    day + seconds_in_days(gigaseconds)
  end

  def self.seconds_in_days(seconds)
    # 60*60*24 = 86400 seconds = 1 day
    seconds/86400
  end

  def self.gigaseconds
    10**9
  end
end
