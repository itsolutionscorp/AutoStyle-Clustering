require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    date + Rational(10**9, 86400) - Rational(6400, 86400)
  end

end
