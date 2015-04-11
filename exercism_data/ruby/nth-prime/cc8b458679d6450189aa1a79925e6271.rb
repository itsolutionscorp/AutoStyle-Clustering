require 'prime'

class Prime
	def self.nth(num)
		raise ArgumentError, "Number must be greater than 0" if num <= 0
		first(num).last
	end
end
