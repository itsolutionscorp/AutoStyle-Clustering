require 'date'
require 'time'

class Gigasecond

	def self.from(birthday)
		
		
		gs = birthday.to_date + Rational(10**9, 86400)
	end

end
