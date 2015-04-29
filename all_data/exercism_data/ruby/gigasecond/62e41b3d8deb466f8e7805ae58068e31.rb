require 'date'
require 'time'

GS_IN_DAYS = (1000000000 / 60 / 60 / 24)
GS = 1000000000

class Gigasecond
	def self.from(date)
		if date.is_a? Date
			date + GS_IN_DAYS
		elsif date.is_a? Time
			(date + GS).to_date
		end
	end
end
