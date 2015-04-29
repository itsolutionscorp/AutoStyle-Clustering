require 'date'
require 'time'

class Gigasecond	
	def self.from(startingDate)		
		gigaSecond = 10**9				
		(startingDate.to_time + gigaSecond).to_date		
	end
end
