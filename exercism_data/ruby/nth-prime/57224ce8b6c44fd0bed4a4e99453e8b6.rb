class Prime
	def self.nth(n)
		raise ArgumentError, "Argument must be an integer greater than or equal to 1" if n < 1
		count = 0; number = 1
		until count == n 
			number += 1 
			count += 1 if number.is_prime()
		end
		number 
	end
end

class Fixnum
	def is_prime
		return false if self < 2
		(2..Math.sqrt(self)).each do |i|
			return false if self % i == 0
		end
		return true
	end
end
