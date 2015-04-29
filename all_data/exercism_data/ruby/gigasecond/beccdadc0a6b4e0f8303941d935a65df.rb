class Gigasecond
  NumberOfSecondsInAGigasecond = 10 ** 9

  def self.from(time)
    add_seconds_to_time(time, NumberOfSecondsInAGigasecond)
  end

  def self.add_seconds_to_time(time, seconds)
    Time.at(time.to_i + seconds)
  end
end
