class Gigasecond

	def initialize(date)
		@date = date
	end
	
	SEC_PER_DAY = 24 * 3600
	R = Rational(10 ** 9, SEC_PER_DAY)
	
	def date
		(@date + R.to_int).to_date
	end
	
end
