require 'date'

class Gigasecond
  def self.from(date)
    date + Rational(1e9, 60*60*24).floor
  end
end
