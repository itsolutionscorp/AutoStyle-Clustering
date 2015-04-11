require 'date'
require 'time'

class Gigasecond
	def self.from(input_date)
		
		gigasecond = 10**9
        (input_date.to_time + gigasecond).to_date
	
	end
end
