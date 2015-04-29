require 'date'
require 'time'

class Gigasecond

	def initialize (date)
		seconds_as_days = (10**9)/60/60/24
		@date = date + seconds_as_days
	end

	attr_accessor (:date)

end
