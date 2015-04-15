require 'date'
require 'time'

class Gigasecond	
	def self.from(startingDate)
		# a gigasecond is 10**9 seconds
		dayInGigaSeconds = (1.0/86400) * (10**9)

		#add the dayInGigaSeconds to the starting date
		startingDate + dayInGigaSeconds.to_int
		#Date.new(newDate.year, newDate.month, newDate.day)
	end
end
