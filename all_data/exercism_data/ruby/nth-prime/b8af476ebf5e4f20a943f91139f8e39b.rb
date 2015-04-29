class Prime

	def self.nth(n)
		raise ArgumentError if n == 0
		count = 2
		primes = []
		until primes.length == n
			primes << count if is_prime(count)
			count += 1
		end
		primes.last
	end

	def self.is_prime(number)
		# 6n +- 1 <= sqrt(number)
		if number <= 3
			return number >= 2
		elsif number % 2 == 0 || number % 3 == 0
			return false
		end
		for i in (5..(number**0.5)).step(6) do
			return false if number % i == 0 || number % (i+2) == 0
		end
		return true
		# (1..(number**0.5)).to_a.select {|i| number % i == 0 }.length == 1
	end

end
