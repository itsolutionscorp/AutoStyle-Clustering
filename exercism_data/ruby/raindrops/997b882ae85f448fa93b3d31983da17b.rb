require 'prime'

class Raindrops
	def self.convert(num)
		prime_factor = true
		raindrop_speak = []
		num_array = num.prime_division
		num_array.each do |i,j|
			case i 
			when 3
				raindrop_speak << "Pling"
				prime_factor = false
			when 5
				raindrop_speak << "Plang"
				prime_factor = false
			when 7
				raindrop_speak << "Plong"
				prime_factor = false
			end
		end
		raindrop_speak << num if prime_factor
		raindrop_speak.uniq.join
	end
end
