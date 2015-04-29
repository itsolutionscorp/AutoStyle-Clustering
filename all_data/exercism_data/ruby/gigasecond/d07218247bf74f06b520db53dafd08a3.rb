class Gigasecond
	GSECS = 10**9

	def initialize(start_date)
		@start_date = start_date
		
	end

	def date
		return (@start_date.to_time + GSECS).to_date
	end
end
