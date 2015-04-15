require 'date'

class Gigasecond

	def self.from date
		if date.class == Time 
			Date.parse( ( date + 10**9 ).to_s ) 
		else
			date + 10**9 / ( 60 * 60 * 24 )
		end
	end

end
