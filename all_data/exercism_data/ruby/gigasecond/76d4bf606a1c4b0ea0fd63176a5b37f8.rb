class Gigasecond
	def self.from (date)
		gs = 10**9
		date + (gs/60/60/24)	
	end
end
