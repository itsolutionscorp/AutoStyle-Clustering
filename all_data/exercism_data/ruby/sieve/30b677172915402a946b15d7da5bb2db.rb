class Sieve
	attr_reader:primes
	def initialize(n)
		@primes = [*2..n]
		@primes.each do |m| 
			test = m
			until test > n do
				test += m
				@primes.delete(test)
			end
		end
	end
end
