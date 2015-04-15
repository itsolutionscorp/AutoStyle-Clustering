class Gigasecond
  DURATION = 10**9

  def self.from(date)
    gigasecond = date.to_time + DURATION
    timestamp = gigasecond.to_s
    date.class.parse timestamp
  end
end
