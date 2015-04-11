require 'date'
require 'time'

class Gigasecond	
	def self.from(startingDate)		
		gigaSecond = 10**9		
		(Time.parse(startingDate.to_s) + gigaSecond).to_date
		
	end
end
