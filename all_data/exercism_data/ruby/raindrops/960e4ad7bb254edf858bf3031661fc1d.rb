class Raindrops
	
	def self.convert num
		sound_to_return = self.sound(num)
		if sound_to_return.size == 0
			sound_to_return = num.to_s()
		end
		sound_to_return
	end
	private
	def self.sound number 
		sounds = {
			3 => "Pling",
			5 => "Plang",
			7 => "Plong"
		}

		sound_string = ""

		sounds.each_pair do |prime, sound|
			if number % prime == 0
				sound_string += sound
			end
		end
		sound_string
	end
end
