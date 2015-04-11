#!/usr/bin/env ruby

class Gigasecond 
		
	def self.from(date)
		input_as_time = date.to_time + (10**9)
		input_as_date = input_as_time.to_date
		input_as_date
	end
end
