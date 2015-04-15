require 'date'
require 'time'
gem 'activesupport'
require 'active_support/all'

class Gigasecond
		def self.from(givendate)
		date_style = '%a, %d %b %Y'
		givendate += (10**9).seconds
	  gs = givendate.strftime(date_style)
	   Date.parse(gs)
	end
end
