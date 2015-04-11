require 'date'

class Gigasecond 
	def self.from(from_date)
		time = from_date.to_time + 10**9 
		time.to_date
	end
end
