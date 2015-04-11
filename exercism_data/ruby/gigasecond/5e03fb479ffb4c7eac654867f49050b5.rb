require 'time'
require 'date'

class Gigasecond
	def self.from(birthday)
		return birthday + (10**9)
	end
end
