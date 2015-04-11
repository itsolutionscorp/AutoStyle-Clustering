require 'date'
require 'time'

class Gigasecond 
  class << self  
    def from(date)
      converted_gigasecond_to_days = 10**9/86400
      date.next_day(converted_gigasecond_to_days)
    end
  end
end
