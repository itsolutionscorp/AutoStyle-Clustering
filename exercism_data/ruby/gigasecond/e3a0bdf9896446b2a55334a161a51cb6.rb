require 'date'

class Gigasecond
	def self.from(date)
		days = (10**(9)) / 60 / 60 / 24
		date += days
		return date
	end
end
