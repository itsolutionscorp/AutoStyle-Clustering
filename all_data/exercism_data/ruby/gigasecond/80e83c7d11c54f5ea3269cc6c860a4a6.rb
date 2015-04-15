# hamming -- exercism.io
# author: fractalic

module Gigasecond
	attr :GIGASECONDS_DAYS
	@GIGASECONDS_DAYS = 10**9/86400
	
	# Add a billion seconds to the given date.
	def Gigasecond.from(start)
		return start+@GIGASECONDS_DAYS
	end
end
