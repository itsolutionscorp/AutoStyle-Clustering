require 'date'
require 'time'

class Gigasecond
	def Gigasecond.from(date)
		x = date.to_time
		Date.parse(Time.at(x.to_i+10**9).to_s)
	end
end
