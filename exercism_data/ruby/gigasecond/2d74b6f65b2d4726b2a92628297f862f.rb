#Class Gigasecond returns the Date object for a given Date + a GIGASECOND (10^9)
require 'date'
require 'time'
class Gigasecond
	GIGASECOND = 10**9
	def self.from( birth )
		(birth.to_time + GIGASECOND).to_date
	end

end
