module Gigasecond
require 'date' 
	def self.from(date)
		# 1Gs == 11574.07407 days
		date + 11574
	end
end
