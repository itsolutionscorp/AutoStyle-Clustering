class Gigasecond
  ONE_GIGASECOND = 1_000_000_000
  SECONDS_IN_A_DAY = 24 * 60 * 60

  def initialize(date)
    @birthday = date
  end

  def date
    @birthday += Rational(gigaseconds_per_day.to_s)
  end

  def gigaseconds_per_day
    @result ||= ONE_GIGASECOND / SECONDS_IN_A_DAY
  end

end
