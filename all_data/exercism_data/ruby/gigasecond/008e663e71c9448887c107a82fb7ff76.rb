class Gigasecond

	DAYS = 10**9 / (60*60*24)

	def initialize(date)
		@date = date
	end

	def date
		return @date + DAYS
	end

end
