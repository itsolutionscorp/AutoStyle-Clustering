require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    date + ( 10 ** 9 / 3600/ 24 ) # in brackets is conversion of Gigaseconds to days
    # Rational(10**9, 86400) - Rational(6400, 86400) # another way to convert
    # GS to days
  end

end
