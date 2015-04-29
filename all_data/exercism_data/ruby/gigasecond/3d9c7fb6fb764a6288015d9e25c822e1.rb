require 'date'
require 'time'

class Gigasecond
	def self.from(date)
		date + days_in_gigasecond()
	end
	
	private
	Seconds_in_gigasecond = 10**9
	Seconds_in_day = 60*60*24
	def self.days_in_gigasecond()
		Seconds_in_gigasecond / Seconds_in_day
	end
end
