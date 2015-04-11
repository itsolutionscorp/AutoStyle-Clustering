require 'date'

class Year
	def self.leap?(year)
		(year % 400).zero? || (year % 4).zero? && (year % 100).nonzero?
	end
end
