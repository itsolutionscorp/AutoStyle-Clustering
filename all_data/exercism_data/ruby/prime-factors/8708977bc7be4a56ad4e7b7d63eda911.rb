class PrimeFactors

	def self.for(number)
		return [2] if number == 2	
		prime_factors = []
		product = 1
		
		self.primesUpTo( (number/2).to_int ).each do |i|
			if number % i == 0 
				prime_factors << i 
				product = prime_factors.inject(product) { |product, prime_factor| product * prime_factor }
				break if product == number
				number = number / i
				redo
			end
		end
	prime_factors = prime_factors.sort
	end
	
	def self.primesUpTo(limit)
		primes = [2,3]
		(3..limit).step(2) do |i|
			if self.isPrime(i)
				primes << i
			end
		end
		primes
	end
	
	def self.isPrime(n)
		if n == 2
			return true
		elsif ( n % 2 == 0 && n != 2 )
			return false 
		elsif 
			n_root = Math.sqrt(n)
			(3..n_root).step(2) do |i|
				return false if n % i == 0 
			end
		else return true	
		end
	end
end
