class Gigasecond
  GIGASECOND = 1e9
  SECONDS_PER_DAY = 86400
  GIGASECOND_FRACTION = Rational(GIGASECOND, SECONDS_PER_DAY)

  def self.from date
    result = date + GIGASECOND_FRACTION
    # We got the precise result now. The tests want the day at 0:00
    Date.jd(result.jd)
  end
end
