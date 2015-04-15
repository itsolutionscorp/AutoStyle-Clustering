require 'date'
GIGA_SECOND = 10**9

class Gigasecond
	def self.from (date)
		(date.to_time + GIGA_SECOND).to_date
	end
end
