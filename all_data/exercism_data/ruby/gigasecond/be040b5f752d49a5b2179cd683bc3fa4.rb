require 'date'

class Gigasecond 
	def self.from(from_date)
		date = from_date.to_time + 10**9 #gigasecond_to_days
		date.to_date
	end
end
