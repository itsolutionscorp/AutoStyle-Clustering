#!/usr/bin/env ruby
# encoding: UTF-8

class Gigasecond
	GIGASECOND = 1_000_000_000

	def self.from(date)
		# Convert input date to seconds
		@seconds = date.to_time.to_i
		self
	end

	def self.date
		# Return date of first gigasecond since input date
		Time.at(GIGASECOND+@seconds).to_date
	end

end
