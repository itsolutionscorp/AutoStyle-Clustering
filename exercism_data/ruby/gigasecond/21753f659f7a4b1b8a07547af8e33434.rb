require 'date'
require 'time'

class Gigasecond
	def initialize(date_of_birthday)
		@date_initial = date_of_birthday.to_time
	end

	def date
		giga_birth = (date_initial + 10**9).to_date
		return giga_birth
	end
end
