require 'date'
require 'time'

class Gigasecond
Giga = 10**9

	def self.from(bDay)
		bTime = bDay.to_time
		bTime += Giga
		return bTime.to_date
	end

end
