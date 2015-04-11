require 'date'
require 'time'

class Gigasecond
	def self.from(date)
		result = date.to_time
		result += 10**9
		result.to_date
	end
end

#puts Gigasecond.from(Date.new(2011, 4, 25))
