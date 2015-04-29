class Gigasecond
  GIGA_SECOND = 1000*1000*1000

  def self.from(start_date)
    start_date + GIGA_SECOND
  end
end
