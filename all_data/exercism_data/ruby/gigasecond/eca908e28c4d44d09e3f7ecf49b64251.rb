require 'date'

class Gigasecond
	def self.from(date)
		gs = date.to_time
		gs = gs + (10**9)
		Date.new(gs.year, gs.month, gs.day)
	end
end
