require 'date'
require 'time'

class Gigasecond

	def Gigasecond.from(start)
		gs_date = start.to_time + 10**9
		return gs_date.to_date
	end

end
