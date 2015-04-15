class Gigasecond
	def self.from(date)
		time_distance = in_days
		computed_date = date + time_distance
		computed_date.to_datetime.to_date
	end
	
	private
	SECONDS_PER_DAY = 86400
	SECONDS_IN_GIGASECOND = 1000000000

	def self.in_days
		Rational(SECONDS_IN_GIGASECOND, SECONDS_PER_DAY)
	end
end
