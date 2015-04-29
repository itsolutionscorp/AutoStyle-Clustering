require 'date'
require 'time'

class Gigasecond
	GIGA_SECOND = 10**9
	def self.from(aDate)
		a_time = aDate.to_time
		a_time += GIGA_SECOND
		a_time.to_date
	end
end
