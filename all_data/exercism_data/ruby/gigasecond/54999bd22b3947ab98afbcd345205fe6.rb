require 'date'
require 'time'

class Gigasecond

	def self.from(a)
		a = a.to_i
		a += 10**9
		return Time.at(a).utc
	end

end
