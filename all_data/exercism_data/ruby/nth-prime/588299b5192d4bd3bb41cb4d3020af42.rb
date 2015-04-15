require 'prime'
class Prime
	def nth(integer)
		nth_prime = Prime.take(integer).last
		if nth_prime == nil
			raise ArgumentError 
		else
			nth_prime
		end
	end
end
