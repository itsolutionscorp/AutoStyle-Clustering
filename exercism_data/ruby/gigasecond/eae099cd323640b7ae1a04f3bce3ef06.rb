class Gigasecond
  def self.from(date)
    self.time_from(date).to_date
  end

  def self.time_from(date)
    Time.at(Time.new(date.year, date.month, date.day).to_i + self.seconds)
  end

  def self.seconds
    10 ** 9
  end
end
