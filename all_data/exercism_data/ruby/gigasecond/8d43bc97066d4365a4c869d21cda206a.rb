require 'date'
require 'time'

# Calculates the date one billion seconds after the submitted date
class Gigasecond

  ONE_BILLION = 1000000000
  
  def self.from(date)
    t = date.to_time
    t += Gigasecond::ONE_BILLION
    t.to_date
  end
end
