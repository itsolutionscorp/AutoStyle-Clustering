require 'prime'

class Prime
	def nth(argument)
		nth_prime = Prime.first(argument).last
		raise ArgumentError, 'Does not compute' unless nth_prime != nil  
		return nth_prime
	end
end
