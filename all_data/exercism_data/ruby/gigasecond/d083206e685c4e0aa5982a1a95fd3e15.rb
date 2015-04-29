require 'date'
require 'time'

class Gigasecond
Giga = 10**9

	def self.from(bDay)
		return (bDay.to_time + Giga).to_date
	end

end
