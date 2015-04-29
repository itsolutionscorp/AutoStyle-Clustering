require 'prime'

class Prime
	
	def self.nth(number)
		raise ArgumentError if number == 0
		first(number).last
	end
end
