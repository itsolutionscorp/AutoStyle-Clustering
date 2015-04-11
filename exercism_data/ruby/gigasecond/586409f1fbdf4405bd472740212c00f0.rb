require 'date'
require 'time'

GIGASECOND = 10 ** 9

class Gigasecond
	def self.from (birth_date)
		gigadate = birth_date.to_time.to_i + GIGASECOND;
		return Date.strptime(gigadate.to_s,'%s')
	end
end
