class Prime
	
	@@primes = [2] 
	@@j = 0 
	@@last = 3

	def self.nth(n)
		raise ArgumentError.new if n == 0
		return @@primes[n-1] unless @@primes[n-1] == nil
		(@@last..200000).step(2) do |i|
			if self.isPrime(i)
				@@primes << i
				@@j += 1
				@@last = i
			end
			break if @@j == n
		end
		@@primes[n]
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

Prime.nth(6)
