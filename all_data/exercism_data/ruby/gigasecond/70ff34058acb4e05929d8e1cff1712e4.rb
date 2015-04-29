require 'time'
require 'date'

class Gigasecond
	def self.from(date)
		time = date.to_time
		gigaseconds = 10**9
		(time + gigaseconds).to_date
	end

end
