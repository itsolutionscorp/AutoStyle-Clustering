require 'time'
require 'date'

class Gigasecond
	def self.from(a)
		jump = 1000000000
		gs = (a.to_time + jump).to_date
	end
end
