class Gigasecond
	def self.from(date)
		gigasecond = 10**9/60/60/24
		date + gigasecond
	end
end
