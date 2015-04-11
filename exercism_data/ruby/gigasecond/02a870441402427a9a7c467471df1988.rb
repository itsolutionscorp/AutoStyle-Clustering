require 'date'
require 'time'

class Gigasecond
	class << self

		GIGASECOND = 10**9

		def from(birthday)
			
			gs = Date.new(birthday.year, birthday.month, birthday.day) + (GIGASECOND/60/60/24)

			gs

		end

	end
end
