class Gigasecond
  DAYS_IN_GIGASECONDS = 11574

  def self.from(dt)
    dt + DAYS_IN_GIGASECONDS
  end
end
