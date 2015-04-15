#!/usr/bin/env ruby

class Prime
	def self.nth(n)

		raise ArgumentError, 'Argument must be an integer greater than or equal to 1' unless (n.is_a? Integer) && (n>0)
		
		if n == 1
			return 2
		else	
			prime_index = 1
			x = 2
			while prime_index < n do
				x += 1
				if isprime(x)
					prime_index += 1
				end
			end
		end
		return x
	end

	def self.isprime(integer_input)
		(2..Math.sqrt(integer_input).ceil).each do |x|
			if (integer_input % x == 0)
				return false
			end
		end
		return true
	end
end
