class Gigasecond
  GIGA = 10 ** 9

  def self.from(time)
   (time.to_time + GIGA).to_date
  end
end
