require 'date'

class Gigasecond
  def self.from(date)
    gs = date.to_datetime + Rational(1, 24 * 60 * 60) * (10 ** 9)
    gs.to_date
  end
end
