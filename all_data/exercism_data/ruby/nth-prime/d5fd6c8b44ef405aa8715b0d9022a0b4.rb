class Prime
	require 'prime' 
	def self.nth(number)
		if number == 0 || number <=0
			raise ArgumentError
		else
			prime_array = Prime.first number
			result = prime_array.at(number-1)
		end
	end
end
