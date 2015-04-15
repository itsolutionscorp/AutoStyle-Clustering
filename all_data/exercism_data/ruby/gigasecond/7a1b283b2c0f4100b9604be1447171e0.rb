require 'date'
require 'time'

class Gigasecond
	def self.from (date)
		date + days_in_gigasec
	end

	def self.days_in_gigasec
		gigasec = 10**9
		secs_per_day = 60*60*24
		gigasec / secs_per_day
	end
end
