require 'date'
require 'time'

class Gigasecond
	def self.from(date)
		days = ((10**9/60)/60)/24
		date+= days
	end
end
