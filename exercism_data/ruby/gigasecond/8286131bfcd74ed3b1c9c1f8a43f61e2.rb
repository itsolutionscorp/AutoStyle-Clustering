module Gigasecond
  SECONDS_IN_DAY = 24 * 60 * 60
  GIGASECOND = 1_000_000_000

  def self.from date
    with_time = date + Rational(GIGASECOND, SECONDS_IN_DAY)
    with_time - with_time.day_fraction
  end
end
