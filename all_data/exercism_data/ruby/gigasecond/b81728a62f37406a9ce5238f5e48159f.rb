require "date"
require "active_support/core_ext"

class Gigasecond
	def self.from(from_date)
		Date.parse(calculate_gigasecond_anniversary(from_date).to_s)
	end

	private

	
	def self.calculate_gigasecond_anniversary(from_date)
		(10**9).seconds.since from_date
	end
end
