class Gigasecond
	GIGASECOND = 10**9
	class << self
		def from(date)
			(date.to_time + GIGASECOND).to_date	
		end
	end
end
