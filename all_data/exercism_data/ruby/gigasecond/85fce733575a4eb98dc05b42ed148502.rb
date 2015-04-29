require 'date'
require 'time'

class Gigasecond
   DAYS_IN_A_GIGASEC = 1_000_000_000/(24*60*60)

  def self.from(start_date)
    start_date + DAYS_IN_A_GIGASEC
  end
end
