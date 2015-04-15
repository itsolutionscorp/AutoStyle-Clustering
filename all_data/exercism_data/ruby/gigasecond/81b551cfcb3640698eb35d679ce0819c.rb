class Gigasecond
  def self.from(day)
    day + in_days
  end

  def self.in_days
    # 60*60*24 = 86400 seconds = 1 day
    10**9/86400
  end
end
