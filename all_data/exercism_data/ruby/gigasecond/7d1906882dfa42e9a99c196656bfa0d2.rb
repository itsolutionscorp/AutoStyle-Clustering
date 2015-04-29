require 'date'
require 'time'

class Gigasecond
	def date()
		(@dateb.to_time + (10**9)).to_date
	end

	def initialize(dateb)
		@dateb = dateb
	end 

end
