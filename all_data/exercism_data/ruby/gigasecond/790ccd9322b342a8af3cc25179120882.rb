require 'date'

module Gigasecond
	extend self
		GS = (10**9)
		
		# Public: Calculates a Gigasecond from date
		#
		# date - A Date or DateTime object
		#
		# Returns a Date object with (10**9) seconds added

		def from(date)
			a = date.to_time + GS
			a.to_date
		end
	
end
