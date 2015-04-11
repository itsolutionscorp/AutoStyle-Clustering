require 'prime'

class PrimeFactors

#minimize time for factoring smaller integers
#by fetching prime numbers in groups of 10000
	NEXT_PRIMES = 10000

	def self.for(integer)
		primes = []
		result = []
		x = integer
		fetch_size = 0		
		while x > 1
		  fetch_size += NEXT_PRIMES
		  primes = Prime.first(fetch_size).last(NEXT_PRIMES)
		  index = 0
		  while index < NEXT_PRIMES && x > 1
		  	prime_no = primes[index]
				while x % prime_no == 0 
					result << prime_no
					x /= prime_no
				end
				index += 1
			end
		end
		result
	end
	
end
