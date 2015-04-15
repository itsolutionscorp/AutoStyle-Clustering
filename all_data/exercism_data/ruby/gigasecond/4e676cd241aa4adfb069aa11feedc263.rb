require 'date'
require 'time'

class Gigasecond
	def initialize(input_date)
		@res_date = input_date
	end

	def date
		@res_date + ((10 ** 9) / (60 * 60 * 24))
	end
end
