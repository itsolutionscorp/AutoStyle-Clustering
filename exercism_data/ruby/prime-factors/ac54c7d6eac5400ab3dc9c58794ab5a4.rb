class PrimeFactors
	def self.for(number)
		prime_factors = []
		num = 2
		temp = number
		while num <= number
			if number % num == 0
				prime_factors.push num
				number = number/num
			else
				num = num + 1
			end

		end
		return prime_factors.pop temp
	end
end
