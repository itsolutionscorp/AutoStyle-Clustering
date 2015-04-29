require 'date'
require 'time'
require 'chronic'

 
class Gigasecond 
	Giga_seconds = (10**9/(60*60*24))
# def self.from("x", "y", "z")
# 	#Conversion en giga secondes
# 	# gos =  Chronic.parse("#{10**9} seconds from #{date}")
# 	gs = 10**9
# 	date = Date.new(x, y, z)
# 	result = date + gs
# 	return result
# end

# def from 
# end


# def gs

# end

def self.from(date)
 	date + Giga_seconds
end

end
