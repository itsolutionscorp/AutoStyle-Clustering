require 'date'

class Gigasecond

	SECONDS_IN_GIGASECOND = 1_000_000_000
	
	def self.from date
		if date.class == Time 
			Date.parse( ( date + SECONDS_IN_GIGASECOND ).to_s ) 
		else
			date + SECONDS_IN_GIGASECOND / ( 60 * 60 * 24 )
		end
	end

end
