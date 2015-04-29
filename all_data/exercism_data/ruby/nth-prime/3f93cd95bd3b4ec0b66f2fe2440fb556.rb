class Prime

	def self.is_prime(number)
		return true if number == 2
		(2..Math::sqrt(number)+1).each{|test| return false if number % test == 0}
		true
	end

	def self.nth(number_prime)
		primes = [2]
		i = 3
		raise ArgumentError if number_prime < 1
		until primes.size == number_prime
			primes << i if is_prime(i)
			i += 2
		end
		primes[-1]
	end

end
