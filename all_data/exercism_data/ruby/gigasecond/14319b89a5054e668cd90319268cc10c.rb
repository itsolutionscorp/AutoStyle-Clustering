require 'date'
require 'time'
class Gigasecond
	def self.from(birthdate)
		gigaday = Date.jd(birthdate.jd + 11574)
	end
end
