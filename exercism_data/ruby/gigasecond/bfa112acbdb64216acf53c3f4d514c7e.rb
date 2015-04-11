require 'date'
require 'time'

class Gigasecond

	def initialize(date)
		@date = date
	end

	def date
		addGigasecond(@date)
	end

	def addGigasecond(date)
		time = date.to_time
		new_time = time + (10**9)
		new_date = new_time.to_date
	end

end
