require 'date'
require 'time'

class Gigasecond
	Gigasecond_calculation = 10**9

	def self.from(date)
		(date.to_time + Gigasecond_calculation).to_date
	end

end
