require 'date'
require 'time'

class Gigasecond
	GIGA = 10 ** 9
	def initialize(date)
		@date = date.to_time.to_i
	end

	def date
		return Time.at(@date + (GIGA)).to_date
	end
end
