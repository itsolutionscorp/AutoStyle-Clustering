require 'date'
require 'time'

class Gigasecond 
 
  def self.from(date)
    gigasecond_in_days = 1000000000/60/60/24 
    date + gigasecond_in_days
  end
end
