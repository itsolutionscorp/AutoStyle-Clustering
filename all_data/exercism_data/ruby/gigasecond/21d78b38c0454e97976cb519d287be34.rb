require "bigdecimal"

class Gigasecond

  GIGASECOND = Rational(1, 24 * 60 * 60) * 10**9

  def self.from(date)
    time = date + GIGASECOND
    Date.new(time.year, time.month, time.day)
  end
end
