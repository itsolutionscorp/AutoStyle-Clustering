class Raindrops
	RAINMAP = {
		3 => 'Pling',
		5 => 'Plang',
		7 => 'Plong'
	}

	def self.convert(number)
		x = ''
		RAINMAP.each_key{|factor| x += RAINMAP[factor] if is_factor(factor, number)}
		if x == '' then number.to_s else x end
	end

	def self.is_factor(f, n)
		n % f == 0
	end
end
