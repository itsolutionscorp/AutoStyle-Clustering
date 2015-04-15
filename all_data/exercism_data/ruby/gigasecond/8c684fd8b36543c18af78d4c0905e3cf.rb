require 'date'
require 'time'

class Gigasecond
	def self.from(date)
		t = date.to_time
		t = t + 10**9
		return t.to_date
	end
end
