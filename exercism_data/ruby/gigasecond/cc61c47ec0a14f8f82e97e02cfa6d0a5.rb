class Gigasecond

  # Initialize with a start date 
	def initialize(start_date)
		@start_date = start_date
	end

  # Returns the date falling 1B seconds after the start date
	def date()
		(@start_date.to_time + (10**9)).to_date
	end
end
