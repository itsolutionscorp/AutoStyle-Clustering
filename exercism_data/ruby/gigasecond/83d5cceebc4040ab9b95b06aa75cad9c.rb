class Gigasecond
	def self.from(start_Date)
		@days_constant = ((10**9)/(24*60*60)).ceil
		@init_year = start_Date + (@days_constant)
		@init_year
	end
end
