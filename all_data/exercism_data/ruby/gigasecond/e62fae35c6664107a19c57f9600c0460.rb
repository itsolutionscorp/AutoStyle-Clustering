require 'date'
require 'time'

class Gigasecond
	def Gigasecond.from(from_date)
		giga_seconds = 10**9
		time = from_date.to_time
		time += giga_seconds
		Date.new(time.year, time.mon, time.day)
	end

end
