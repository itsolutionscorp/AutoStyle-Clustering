require 'date'
require 'time'

class Gigasecond
	def self.from(date)
		year = 1992
		start = date.to_time
		day = start + 1000000000
		#date.to_time
		final = day.to_date
		#puts "#{final}"
		final
	end
	
end

#g=Gigasecond.new()
#g.from(Date.new(2011, 4, 25))
