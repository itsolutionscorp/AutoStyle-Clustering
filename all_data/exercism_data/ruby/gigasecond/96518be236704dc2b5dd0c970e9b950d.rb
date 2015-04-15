require 'date'

module Gigasecond
	def self.from(date)
		date += sec_to_days(10**9)
	end

	def self.sec_to_days(seconds)
		seconds / (3600 * 24)
	end
end
