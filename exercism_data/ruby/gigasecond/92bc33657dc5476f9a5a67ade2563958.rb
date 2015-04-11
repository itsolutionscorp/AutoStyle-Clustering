class Gigasecond
	def initialize(date)
		@date = date
	end

	def date()
		@date += Rational(10**9 / (24*3600))
	end
end
