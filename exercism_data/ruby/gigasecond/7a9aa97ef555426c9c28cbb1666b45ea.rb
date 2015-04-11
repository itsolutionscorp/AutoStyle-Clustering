require 'date'
require 'time'

GIGASECOND = 10 ** 9

class Gigasecond
	def self.from (birth_date)
		gigadate = birth_date.to_time + GIGASECOND;
		return gigadate.to_date
	end
end
