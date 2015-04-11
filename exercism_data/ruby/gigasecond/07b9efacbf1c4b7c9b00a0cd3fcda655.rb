#!/usr/bin/env ruby
# encoding: UTF-8

class Gigasecond
	GIGASECOND_IN_DAYS=11574 # 1_000_000_000/(60*60*24)

	def self.from(date)
		@original_date = date
		self
	end

	def self.date
		@original_date+GIGASECOND_IN_DAYS
	end

end
      
