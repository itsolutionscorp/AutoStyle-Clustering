require 'date'
require 'time'

class Gigasecond
	def self.from( date)
		date + days_in_gigasecond()
	end
	
	private
	def self.days_in_gigasecond()
		10**9 / (60*60*24)
	end
	
end
