require 'date'
require 'time'

class Gigasecond
	def initialize(date)
		@user_date = date
	end

	def date
		gs = 10**9
		date = gs/60/60/24
		@user_date + date
	end
end
