require 'date'
require 'time'
require 'pry'

class Gigasecond
	def initialize(date)
		@gig = date
	end

	def date
		time = Time.mktime(@gig.year, @gig.month, @gig.day)
		@gig = (time + (10**9)).to_date
	end
end
