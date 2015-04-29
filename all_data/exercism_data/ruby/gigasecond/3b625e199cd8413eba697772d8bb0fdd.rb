require 'date'
require 'time'

class Gigasecond
#A gigasecond is one billion (10**9) seconds.
GIGA_SEC = 10**9
#which is
# 11574 days
#     1 hours
#     46 minutes
#     40 seconds
 	
 	def initialize(date)
 		@birth_date = date
 	end

  #convert to time format, as this does math in seconds
  def date
    (@birth_date.to_time + GIGA_SEC).to_date    
  end

end

