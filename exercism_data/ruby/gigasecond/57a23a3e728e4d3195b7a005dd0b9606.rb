class Gigasecond 
	def self.from(date)
		gigasecond_in_days = (10**9)/60/60/24
		date += gigasecond_in_days
		date
	end
end
