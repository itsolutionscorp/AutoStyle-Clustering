class Gigasecond
  def self.from(start)
    (as_time(start) + gigasecond).to_date
  end

  def self.as_time(day)
    day.to_time
  end

  def self.gigasecond
    10**9
  end
end
