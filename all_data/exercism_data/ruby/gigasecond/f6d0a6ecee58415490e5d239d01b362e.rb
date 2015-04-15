class Gigasecond
	GIGASECOND_IN_DAYS = 1_000_000_000 / 60**2 / 24
	
	def initialize(date)
		@base_date = date
	end

	def date
		gigaversary = @base_date + GIGASECOND_IN_DAYS
	end
end
