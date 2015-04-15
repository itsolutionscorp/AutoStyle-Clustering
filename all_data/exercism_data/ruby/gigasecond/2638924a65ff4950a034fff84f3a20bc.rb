module Gigasecond
	
	extend self	
	GS_TO_DAYS = 10**9 / (60*60*24)

	def from (date)
		date += GS_TO_DAYS
	end

end
