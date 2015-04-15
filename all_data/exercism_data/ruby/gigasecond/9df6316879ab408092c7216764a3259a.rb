require 'date'
require 'time'
class Gigasecond
	Gigasecond=(10**0)
	def self.from(date_from)
		(date_from.to_time + Gigasecond).to_date
		
	end
end
