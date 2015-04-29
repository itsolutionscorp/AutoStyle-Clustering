require 'date'

module Gigasecond
	def self.from(date)
		days = (10**9) / (60 * 60 * 24)
		return date + days
	end
end
