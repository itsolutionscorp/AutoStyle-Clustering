require 'date'
require 'time'

class Gigasecond

 GSinDays = 11574
 GSinS = (10**9)

 def self.from(input)
	input += GSinDays if input.kind_of?(Date)
	if input.kind_of?(Time)
		input+= GSinS
		input = Date.parse(input.to_s)
	end
	input
 end
end
