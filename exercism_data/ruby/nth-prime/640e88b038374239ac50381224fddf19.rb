require 'prime'

class Prime
	def nth(number)
		return self.first(number).last if number > 0
		raise ArgumentError.new
	end
end
