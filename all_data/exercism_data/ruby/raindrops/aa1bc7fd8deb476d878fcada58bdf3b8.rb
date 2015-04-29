class Raindrops
	PRIME_FACTORS_TO_SOUNDS = { 
		3 => "Pling",
		5 => "Plang",
		7 => "Plong",
	}

	def self.convert(int_number)
		sound_of_raindrops = ''

		PRIME_FACTORS_TO_SOUNDS.each do |prime_factor, sound|
			if (int_number % prime_factor) == 0
				sound_of_raindrops = sound_of_raindrops + sound
			end
		end

		if sound_of_raindrops == ''
			sound_of_raindrops = int_number.to_s
		end

		sound_of_raindrops
	end
end
