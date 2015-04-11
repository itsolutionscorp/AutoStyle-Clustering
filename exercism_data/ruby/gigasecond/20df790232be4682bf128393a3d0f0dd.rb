require 'Rational'

class Gigasecond
  GIGASEC_DAYS = 11574

  def self.from(date)
    date + GIGASEC_DAYS
  end
end
