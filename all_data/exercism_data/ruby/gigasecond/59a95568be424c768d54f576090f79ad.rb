require'date'
require 'time'


class Gigasecond
	def initialize(date)
		@date = (date.to_time + (10**9)).to_date
	end
	attr_reader :date
end
