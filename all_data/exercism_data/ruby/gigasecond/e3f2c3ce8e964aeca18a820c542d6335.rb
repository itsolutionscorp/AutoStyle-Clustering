class Gigasecond

  GIGASECONDS = 10**9
  SECONDS_PER_DAY = 24 * 60 * 60

  def self.from(date)
    date + days_per_gigasecond
  end

  def self.days_per_gigasecond
    GIGASECONDS / SECONDS_PER_DAY
  end

end
