# Rather naive implementation

class Prime
	@@primes = { 1 => 2, 2 => 3 }

	def self.primes
		@@primes
	end

	def self.nth(n)
		if n <= 0 then raise ArgumentError end
		if primes.has_key?(n) then return primes[n] end

		curr_int = 1
		prime_index = 1

		while prime_index <= n do
			curr_int += 1

			if primes.has_key?(prime_index)
				curr_int = primes[prime_index]
				prime_index += 1

			elsif is_prime?(curr_int)
				primes[prime_index] = curr_int
				prime_index += 1
			
			end
		end

		return curr_int
	end

	def self.is_prime?(x)
		half_x = x/2

		for i in 2..half_x
			if x % i == 0
				return false
			end
		end

		return true
	end
end
