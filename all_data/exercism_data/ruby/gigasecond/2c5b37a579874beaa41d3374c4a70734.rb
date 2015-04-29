class Gigasecond
	GigasecondInDays = 1_000_000_000 / 60**2 / 24
	
	def initialize(date)
		@base_date = date
	end

	def date
		gigadate = @base_date + GigasecondInDays
	end
end
