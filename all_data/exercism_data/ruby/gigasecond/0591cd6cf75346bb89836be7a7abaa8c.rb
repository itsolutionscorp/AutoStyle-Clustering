require 'date'

class Gigasecond
	def self.from (date)
		days_in_gigasecond = 10**9 / 60 / 60 / 24
		
		return date + days_in_gigasecond
	end
end
