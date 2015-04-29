require 'prime'

class Prime
	def self.nth(number)
		raise(ArgumentError) if number == 0
		return self.first(number)[-1]
	end
end
