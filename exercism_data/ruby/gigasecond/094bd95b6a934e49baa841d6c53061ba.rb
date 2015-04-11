require 'date'
require 'time'

class Gigasecond

	G_SECONDS = 1_000_000_000

	def self.from(start_date)
		gs = start_date.to_time 
		results = gs + G_SECONDS
		results.to_date
	end

end
