require 'time'
require 'date'

class Gigasecond
	def self.from(date)
		time = date.to_time
		(time + 10**9).to_date
	end

end
