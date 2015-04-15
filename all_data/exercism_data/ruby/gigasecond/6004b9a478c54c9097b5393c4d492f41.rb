class Gigasecond
	
	require 'active_support/time'	
	GIGASECOND = 10**9 # seconds
	
	def self.from(start_date)
		Date.parse((start_date + GIGASECOND.seconds).to_s)
	end
end
