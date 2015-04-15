# - If the number contains 3 as a prime factor, output 'Pling'.
# - If the number contains 5 as a prime factor, output 'Plang'.
# - If the number contains 7 as a prime factor, output 'Plong'.
# - If the number does not contain 3, 5, or 7 as a prime factor,
#   just pass the number's digits straight through.


class Raindrops

	def self.convert(number)
		raindrop_speak = ''
		raindrop_speak << 'Pling' if number % 3 == 0
		raindrop_speak << 'Plang' if number % 5 == 0
		raindrop_speak << 'Plong' if number % 7 == 0
		raindrop_speak.empty? ? number.to_s : raindrop_speak
	end
end
