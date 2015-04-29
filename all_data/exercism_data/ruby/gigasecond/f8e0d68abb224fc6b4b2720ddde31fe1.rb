require 'date'
require 'time'
class Gigasecond
	def initialize(date)
		@date = date
	end

	def date
		giga_time = (Time.parse(@date.to_s) + 1000000000)
		Date.parse(giga_time.to_s)
	end
end
