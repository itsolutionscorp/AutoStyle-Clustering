# GigaSecond
# t0nyLombardi
# 1/23.15

require 'rubygems'
require 'active_support/time'
require 'date'


class Gigasecond
	@gigasecond = 10**9
	def self.from(date)
		t = date+@gigasecond.seconds
		Date.parse(t.strftime("%a, %d %b %Y"))
	end
end
