class Raindrops
	RAINMAP = {
		3 => 'Pling',
		5 => 'Plang',
		7 => 'Plong'
	}

	def self.convert(number)
		drops = RAINMAP.reduce(""){|t, (factor, word)| t + ((number % factor).zero? ? word : "")}
		drops.empty? ? number.to_s : drops
	end

end
