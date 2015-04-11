require 'date'
require 'time'

class Gigasecond
	def Gigasecond.from(from_date)
		giga_seconds = 10**9
		#from_date = Date.new(from_date)
		time = Time.new(from_date.year, from_date.month, from_date.day)
		time += giga_seconds
		Date.new(time.year, time.mon, time.day)
	end

end
