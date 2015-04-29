class Raindrops

	PRIME_TO_SOUND_CONVERSION = {
		3 => 'Pling',
		5 => 'Plang',
		7 => 'Plong'
	}

	def self.convert(number)
		raindrop = Array.new
		PRIME_TO_SOUND_CONVERSION.each do |prime, sound|
			raindrop << sound if number % prime == 0
		end
		raindrop.empty? ? number.to_s : raindrop.join
	end
end
