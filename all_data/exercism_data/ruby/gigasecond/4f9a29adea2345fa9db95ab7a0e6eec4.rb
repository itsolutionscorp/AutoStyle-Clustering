class Gigasecond
  def self.from(date)
    (date.to_time + seconds).to_date
  end

  def self.seconds
    10 ** 9
  end
end
