require "prime"
require "debugger"

class Raindrops

	def self.convert(num)
		sounds = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
		sound = ""
		num.prime_division.each do |el|
			sound += sounds[el.first] if sounds[el.first]
		end
		sound == "" ? sound = num.to_s : sound
	end
	
end
