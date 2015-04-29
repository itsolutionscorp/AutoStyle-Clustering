require 'minitest/autorun'
require 'date'
require 'time'

class Gigasecond
	def self.from(arg)
		a = arg.to_time
		a += Rational((1000000000*86400), 86400)
		return a.to_date
	end

end
