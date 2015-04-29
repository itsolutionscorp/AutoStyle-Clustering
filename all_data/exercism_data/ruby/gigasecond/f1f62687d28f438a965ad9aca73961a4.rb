require 'date'
require 'time'

# Calculates the date one billion seconds after the submitted date
class Gigasecond
  
  def self.from(date)
    t = date.to_time
    t += 1000000000
    t.to_date
  end
end
