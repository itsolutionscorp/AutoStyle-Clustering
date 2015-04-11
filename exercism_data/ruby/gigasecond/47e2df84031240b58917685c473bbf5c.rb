class Gigasecond
	def self.from(date)
		date + 10**9/86400; # billion divided by seconds in 1 day
	end
end
