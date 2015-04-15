require 'prime'

class Prime
	def nth(num)
		max = 100000
		raise ArgumentError, "Argument must be positive integer < #{max}" if (num.is_a? Fixnum and num < 1)
		self.first(max)[num-1]
	end
end
