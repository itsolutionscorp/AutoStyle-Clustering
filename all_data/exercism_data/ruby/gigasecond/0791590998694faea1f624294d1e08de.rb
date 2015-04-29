class Gigasecond

	GS = 10**9
	SECONDS_IN_DAY = 60*60*24
	DAYS = GS/SECONDS_IN_DAY

	def self.from(date)
		date + DAYS
	end

end
