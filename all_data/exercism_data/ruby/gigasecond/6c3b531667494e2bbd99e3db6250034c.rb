require 'time'
require 'date'

class Gigasecond

	def initialize(date)
		@date = date
	end

	def date
		Time.at(@date.to_time.to_i + 10**9).to_date
	end
end
