require 'Prime'

class Prime
	def self.nth(prime)
		if prime == 0
			raise ArgumentError
		else
			array = Prime.first prime 
			return array.last
		end
	end
end
