class Prime

	@@primes = [2, 3]

	def self.nth(n)
		raise ArgumentError, 'n must be greater than zero' if n < 1  
		
		if n-1 <= @@primes.length
			return @@primes[n-1] 
		end

		p = @@primes[-1]
		while @@primes.length < n
			p += 2
			@@primes.push(p) if find_prime_divisors(p) == 0
		end

		p

	end

	private

		def self.find_prime_divisors(n)
			@@primes.count do |prime|
				n % prime == 0
			end
		end

end
