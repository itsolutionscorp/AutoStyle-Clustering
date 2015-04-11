class Gigasecond
  def self.from(date)
    date + gigasecond / seconds_in_a_day
  end

  def self.gigasecond
    10 ** 9
  end

  def self.seconds_in_a_day
    86400
  end
end
