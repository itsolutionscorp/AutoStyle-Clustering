class Raindrops
	RAINMAP = {
		3 => 'Pling',
		5 => 'Plang',
		7 => 'Plong'
	}

	def self.convert(number)
		drops = ''
		RAINMAP.each{|factor,word| drops += word if (number % factor).zero?}
		drops.empty? ? number.to_s : drops
	end

end
