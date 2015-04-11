class Raindrops
# If the number contains 3 as a prime factor, output 'Pling'.
# If the number contains 5 as a prime factor, output 'Plang'.
# If the number contains 7 as a prime factor, output 'Plong'.
# If the number does not contain 3, 5, or 7 as a prime factor,
#   just pass the number's digits straight through.

	require 'prime'

	def self.convert(num)
		prime_factors = num.prime_division
		raindrop_string = ''
		
		prime_factors.each { |fact, mult|
			case fact
				when 3
					raindrop_string += 'Pling'
				when 5
					raindrop_string += 'Plang'
				when 7
					raindrop_string += 'Plong'
			end
		}
		
		return raindrop_string.length == 0 ? num.to_s : raindrop_string
	end
end
