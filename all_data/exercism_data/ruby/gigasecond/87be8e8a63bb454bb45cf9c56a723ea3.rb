require 'date'

class Gigasecond
	def self.from date
		date_in_time = date.to_time
		date_in_gigatime = date_in_time += 10**9
	
	date_in_gigatime.to_date
	
	end	
end
