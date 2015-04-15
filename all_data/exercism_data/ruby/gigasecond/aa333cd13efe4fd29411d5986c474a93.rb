require 'date'
require 'time'

class Gigasecond
	def initialize(date)
		@date = date
	end

	def date()
		return @date + 10**9/60/60/24
	end
end
