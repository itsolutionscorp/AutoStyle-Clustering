class Gigasecond
	def self.from(date)
		date + 1000000000/86400; # billion divided by seconds in 1 day
	end
end
