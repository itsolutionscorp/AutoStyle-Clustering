class Date
	def strip_seconds
		self.to_datetime.to_date
	end
end

class Gigasecond
	def self.from(date)
		computed_date = date + Gigasecond.in_days
		computed_date.strip_seconds
	end
	
	private
	SECONDS_PER_DAY = 86400
	SECONDS_IN_GIGASECOND = 1000000000

	def self.in_days
		Rational(SECONDS_IN_GIGASECOND, SECONDS_PER_DAY)
	end
end
