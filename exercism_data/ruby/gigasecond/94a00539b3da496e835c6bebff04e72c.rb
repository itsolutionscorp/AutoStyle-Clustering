require 'date'

class Gigasecond

	SECONDS_IN_GIGASECOND = 1_000_000_000
	
	def self.from date
		Date.parse( ( date.to_time + SECONDS_IN_GIGASECOND ).to_s ) 
	end

end
