class Gigasecond
	def self.from start_date 
	start_date += days_to_add
	end

	private

	def self.days_to_add
		seconds_in_a_day = 60*60*24
		gigasecond = 1000000000 
		gigasecond/seconds_in_a_day
	end


end
