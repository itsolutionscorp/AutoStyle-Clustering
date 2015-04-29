require 'date'
require 'time'

class Gigasecond
	def initialize(birthday)
		@birthday = birthday
	end

	def date
		(@birthday.to_time + (10**9)).to_date
	end
end
