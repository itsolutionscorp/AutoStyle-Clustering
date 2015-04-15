## large test case takes ~30 secs, but it works ##

class Prime
	def self.nth i
		raise(ArgumentError) unless i > 0
		primes = [2]
		num = 1

		until primes.size == i do
			num += 2
			primes << num if primes.reject { |p| num % p != 0}.empty?
		end

		primes.last	
	end
end
