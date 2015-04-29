require 'Prime'

class Prime

	def self.nth(number)
		if number < 1
			raise ArgumentError, 'Index must be greater than 0.'
		end
		
		Prime.each_with_index do |prime, index|
			return prime if index == number - 1
		end
	end

end
