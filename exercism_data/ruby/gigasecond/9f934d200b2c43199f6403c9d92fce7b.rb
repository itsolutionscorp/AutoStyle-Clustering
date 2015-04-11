require 'date'
require 'time'
class Gigasecond
	attr_reader :minutes, :hours, :days, :gee_ess
	
	def minutes
		60
	end
	def hours
		minutes * 60
	end
	def days
		hours * 24
	end
	
	def gee_ess
		1000000000 / days
		# => 11574
	end

	def self.from(capitald)
		capitald.to_date + 11574
	end



end
