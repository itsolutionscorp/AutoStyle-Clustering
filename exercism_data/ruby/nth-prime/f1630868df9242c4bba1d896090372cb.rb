class Prime
	def self.nth nth
		raise ArgumentError, 'Getting is 0th prime number is silly' if nth == 0

		primes = 0
		i = 1
		while true
			if i.prime?
				primes += 1
				return i if primes == nth
			end
			i += 1
		end
	end
end


class Fixnum
	def prime?
		# 1 is not a prime number ...
		return false if self == 1

		# ... but 2 is 
		return true if self == 2

		# Even numbers are never prime
		return false if [0, 2, 4, 6, 8].include? self.to_s[-1, 1]

		# Test the factors between 2 and the square root of n (which should be
		# enough)
		(2..Math.sqrt(self).ceil).each do |i|
			return false if (self / i.to_f).to_s =~ /\.0$/
		end

		return true
	end
end
