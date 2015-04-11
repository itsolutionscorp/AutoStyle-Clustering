class Gigasecond

  GIGASECOND = 10 ** 9
  SECONDS_IN_DAY = 24 * 60 * 60
  DAYS_IN_GIGASECOND = GIGASECOND / SECONDS_IN_DAY

  def self.from(t)
    return t + DAYS_IN_GIGASECOND
  end
end
