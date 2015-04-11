class Gigasecond
  def self.from(day)
    day = day + gigaseconds_in_days
  end

  def self.gigaseconds_in_days
    # 60*60*24 = 86400 seconds = 1 day
    days = 10**9/86400
  end
end
