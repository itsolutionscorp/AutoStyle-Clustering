#!/usr/bin/env ruby

class Gigasecond
	def self.from(inputdate)
		
		inputdatetime = inputdate.to_datetime
		#puts inputdate
		#puts inputdatetime
		returndatetime = inputdatetime + Rational(10**9, 60*60*24)
		returndate = returndatetime.to_date
		#puts returndate
		#puts returndatetime

		return returndate #Date.new(2043, 1, 1)
	end
end
