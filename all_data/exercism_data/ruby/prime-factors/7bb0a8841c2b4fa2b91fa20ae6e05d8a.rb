class PrimeFactors
	def self.for(number)
		prime_factors, divisor = [], 1
		while number > 1 and divisor += 1
			prime_factors << divisor and number /= divisor while number % divisor == 0
			divisor = number - 1 if divisor > Math.sqrt(number)
		end
		prime_factors
	end
end
