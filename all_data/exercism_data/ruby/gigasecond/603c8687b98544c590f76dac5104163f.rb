class Date
	def beginning_of_day
		self.to_datetime.to_date
	end
end

class Gigasecond
	def self.from(date)
		advanced_date = date + Gigasecond.in_days
		advanced_date.beginning_of_day
	end
	
	private
	SECONDS_PER_DAY = 86400
	SECONDS_IN_GIGASECOND = 1000000000

	def self.in_days
		Rational(SECONDS_IN_GIGASECOND, SECONDS_PER_DAY)
	end
end
