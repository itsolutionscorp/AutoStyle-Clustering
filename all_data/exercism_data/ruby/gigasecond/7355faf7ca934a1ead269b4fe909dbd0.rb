class Gigasecond
	def self.from date
		days = 10**9/(60*60*24)
		gd = date + days
		return gd
	end
end
