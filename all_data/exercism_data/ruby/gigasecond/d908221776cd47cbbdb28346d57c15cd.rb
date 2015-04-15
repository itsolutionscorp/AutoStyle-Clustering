require 'date'
require 'time'

class Gigasecond
	def initialize(indate)
		@date = indate + Rational((10**9), 86400).to_i
	end

	attr_accessor :date
end
