require "prime"

class PrimeFactors

	def self.for(n)
		factors = []

		Prime.each(n) do |prime|
			while n % prime == 0
				factors.push prime
				n /= prime
			end

			break if n == 1
		end

		factors
	end
	
end
