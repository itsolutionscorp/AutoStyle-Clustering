require 'date'
require 'time'

class Gigasecond

	def self.from(birthdate)
		birthdate.to_time + (10**9)
	end
end
