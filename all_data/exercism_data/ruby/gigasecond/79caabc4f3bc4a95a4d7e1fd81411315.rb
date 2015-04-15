require 'date'
require 'time'

class Gigasecond

	def initialize(inputdate)
		secondsInDay = 60 * 60 * 24
		@date = inputdate + (10**9 / secondsInDay)
	end

	attr_reader :date

end
