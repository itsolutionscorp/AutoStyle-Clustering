require 'prime'

class PrimeFactors

	def self.for(num)
		factors = []
		Prime.each do |prime|
			break if prime > num
			while num % prime == 0
				factors << prime
				num /= prime
			end
		end
		return factors
	end

end
