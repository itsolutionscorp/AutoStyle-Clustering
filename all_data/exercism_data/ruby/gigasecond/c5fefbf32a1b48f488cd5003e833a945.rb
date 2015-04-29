require 'date'
require 'time'

class Gigasecond 
	def initialize
	end

	def self.from(date)
		t = Time.at(date.to_time.to_i + 1000000000)
		Date.parse(t.to_s)
	end

	def self.test_urself(date)
		t = Time.at(date.to_time.to_i - 1000000000)
		Date.parse(t.to_s)
	end

end	
