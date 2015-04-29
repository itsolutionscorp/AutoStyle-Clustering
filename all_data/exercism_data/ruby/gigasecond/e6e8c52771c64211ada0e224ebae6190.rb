require 'date'
require 'time'

class Gigasecond
   DAYS_IN_A_GIGASEC = 1000000000/(24*60*60)

  def self.from(d)
    d + DAYS_IN_A_GIGASEC
  end
end
