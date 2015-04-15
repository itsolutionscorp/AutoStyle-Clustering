require 'date'
require 'time'

class Gigasecond
	def self.from(time_input)
		gs = time_input + 999996400.0
		## Time.new(2043, 1, 1, 0 , 46, 40) - Time.new(2011, 4, 25)
		## Time.new(2009, 2, 19, 0, 46, 40) - Time.new(1977, 6, 13)
	end
end
