class Prime
	def self.nth nth
		raise ArgumentError, 'Getting is 0th prime number is silly' if nth == 0

		# Makes the loop slightly easier
		return 2 if nth == 1

		primes = 1
		i = 3
		while true
			if i.prime?
				primes += 1
				return i if primes == nth
			end

			# We start at 3, only odd numbers are prime, so we can +2 (saves a
			# wee bit; about 100 to 200ms)
			i += 2
		end
	end
end


class Fixnum
	# Check if we're a prime number. The first 3 checks aren't required in this
	# case, but it doesn't matter much in performance (I can't even measure for
	# this test-suite, it's in the noise).
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
