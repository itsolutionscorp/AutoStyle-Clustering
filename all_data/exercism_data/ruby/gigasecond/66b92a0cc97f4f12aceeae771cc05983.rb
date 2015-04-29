class Gigasecond
	
	GS_DAYS = (10**9)/(60*60*24)

	class << self
		
		def from(date)
			Date.parse(date.to_s) + GS_DAYS
		end

	end

end
