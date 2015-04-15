class Gigasecond

	DAYS_PER_GIGASECOND = (10**9)/86400

	def self.from(date)
		date + DAYS_PER_GIGASECOND
	end
end
