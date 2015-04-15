require 'prime'

module Raindrops
	def self.convert(number)
		output = ""
		factors = Prime.prime_division(number).map {|subarray| subarray.shift.to_i}
		prime_found = false

		if factors.include?(3)
			output += "Pling"
			prime_found = true
		end
		if factors.include?(5)
			output += "Plang"
			prime_found = true
		end
		if factors.include?(7)
			output += "Plong"
			prime_found = true
		end
		if prime_found == false
			output = number.to_s

		end
	return output
	end
end

