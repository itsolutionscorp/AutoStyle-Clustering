require 'date'
require 'time'

class Gigasecond
	def self.from(birthdate)
		birthdate = birthdate.to_time unless birthdate.is_a? Time
		birthdate += 10**9
		birthdate.to_date
	end
end
