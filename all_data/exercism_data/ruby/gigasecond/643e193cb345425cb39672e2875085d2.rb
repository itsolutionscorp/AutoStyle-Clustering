require 'date'
require 'time'

class Gigasecond

	def self.from(date)
		one_mil = 10**9
		time = date.to_time + one_mil
		return Date.parse(time.to_s)
	end
end
