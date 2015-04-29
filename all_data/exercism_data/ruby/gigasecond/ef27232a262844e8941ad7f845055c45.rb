require 'date'
require 'time'

class Gigasecond
	def self.from(d)
		t = d.to_time
		t = (t +=(10**9)).to_date
		t
	end
end
