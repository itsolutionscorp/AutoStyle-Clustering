require 'time'
require 'date'

class Gigasecond
	Out = Struct.new(:date)
	def self.from(input_date)
		Out.new(input_date + 11574)
	end
end
