class PrimeFactors

	def self.for (num)
		factors = []
		divisor = 2
		while num != 1
			if num % divisor == 0
				factors << divisor
				num /= divisor
			else
				divisor += 1
			end
		end
		factors
	end
end
