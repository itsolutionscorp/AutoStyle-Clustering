require 'date'
require 'time'

class Gigasecond
	def self.from(date)
		time = Time.parse(date.to_s) + (10**9)
		Date.parse(time.to_s)
	end
end
