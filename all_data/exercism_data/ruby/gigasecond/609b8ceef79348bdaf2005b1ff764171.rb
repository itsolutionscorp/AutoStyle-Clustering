class Gigasecond
	GIGASECOND_DAYS = (10**9 / 60.0 / 60.0 / 24.0).floor

	def self.from(date)
		date + GIGASECOND_DAYS
	end
end
