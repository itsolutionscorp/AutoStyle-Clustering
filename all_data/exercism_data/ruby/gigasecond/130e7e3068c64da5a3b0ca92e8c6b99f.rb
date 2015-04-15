require 'date'

class Gigasecond

	@date
	@seconds_in_a_day
	@one_gs_in_days

	def initialize(date)
		@date = date
		@seconds_in_a_day = 60*60*24
		@one_gs_in_days = 10**9 / @seconds_in_a_day
	end

	def date
		@date + @one_gs_in_days
	end

end
