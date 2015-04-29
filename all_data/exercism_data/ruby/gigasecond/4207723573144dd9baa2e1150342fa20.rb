require 'date'
require 'time'

class Gigasecond
	def initialize date
		@date = date
	end

	def date
		(@date.to_time + 1000000000).to_date
	end
end
