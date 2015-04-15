class Gigasecond

  def initialize(date)
    @birthday = date
  end

  def date
    @birthday += Rational(gigaseconds_per_day.to_s)
  end

  def gigaseconds_per_day
    one_gigasecond / seconds_in_a_day
  end

  private

  def one_gigasecond
    1_000_000_000
  end

  def seconds_in_a_day
    24 * 60 * 60
  end

end
