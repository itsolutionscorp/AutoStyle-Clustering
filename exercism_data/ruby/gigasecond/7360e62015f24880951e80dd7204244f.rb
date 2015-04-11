require 'date'
require 'time'

class Gigasecond 
	def self.from(date)
		time = Time.mktime(date.year,date.month, date.day)
		date_gigasecond = Date.parse((time + 10**9).to_s)
		#date_gigasecond = date_gigasecond.to_s)
	end
end
