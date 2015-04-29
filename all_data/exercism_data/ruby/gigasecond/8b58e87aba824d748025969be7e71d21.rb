class Gigasecond
 
	GS_IN_DAYS = (10**9)/(60*60*24)
	 
	def self.from(date)
	date + GS_IN_DAYS
	end
 
end
