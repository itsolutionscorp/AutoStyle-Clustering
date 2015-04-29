require 'date'
require 'time'

class Gigasecond

	def initialize gs_date
		@gs_date = gs_date
	end

	def date
		gs_time = @gs_date.to_time + 10**9
		gs_time.to_date
	end

end
