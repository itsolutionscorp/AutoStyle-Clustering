require 'date'

class Gigasecond
	def initialize(birthday)
		@birthday = birthday
	end

	def date
		Date.parse(gigasecond_birthday.to_s)
	end

	def gigasecond_birthday
		@birthday.to_time + 10**9
	end
end
