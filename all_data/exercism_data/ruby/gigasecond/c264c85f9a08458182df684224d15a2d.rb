require 'date'
require 'time'

class Gigasecond
	def self.from(a)
		gs = 10**9
		return (a.to_time + gs).to_date
	end
end
