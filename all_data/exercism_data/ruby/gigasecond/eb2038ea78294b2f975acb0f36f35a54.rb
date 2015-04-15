require 'date'

class Gigasecond
	def initialize(what)
		@what = what
	end

	def date
	newt = Time.mktime(@what.year, @what.month, @what.day) + 10**9
	return Date.new(newt.year, newt.mon, newt.mday)
	end
end
