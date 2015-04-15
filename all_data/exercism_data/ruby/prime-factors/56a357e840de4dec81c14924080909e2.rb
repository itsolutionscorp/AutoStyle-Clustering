require 'prime'

class PrimeFactors
	def self.for(num)
		primes = []
		Prime.each{|p|
			while num % p == 0
				primes << p
				num /= p
			end

			break if num <= 1
			break if p > num #paranoid? who, me?
		}

		primes
	end
end
