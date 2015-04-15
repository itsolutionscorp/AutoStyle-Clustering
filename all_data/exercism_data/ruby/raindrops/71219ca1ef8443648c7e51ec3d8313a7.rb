require 'prime'

class Raindrops

	SOUND_OF_DROPS = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

	class << self
		def convert(number)
			raindrop_speak = Prime.prime_division(number).map { |factor, exp| SOUND_OF_DROPS[factor] }.join
			return number.to_s if raindrop_speak == ""

			raindrop_speak
		end
	end
end
