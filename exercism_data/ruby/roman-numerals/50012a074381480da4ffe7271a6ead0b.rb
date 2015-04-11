class Fixnum
	def to_roman
		roman = ''
		map = {	1 => "I", 4 => "IV", 5 => "V", 9 => "IX", 10 => "X", 40 => "XL", 50 => "L", 
						90 => "XC", 100 => "C", 400 => "CD", 500 => "D", 900 => "CM", 1000 => "M"}
		remainder = self
		while remainder >= 1
			largest = 0
			map.keys.each do |k|
				if k <= remainder and k > largest
					largest = k
				end
			end
			roman << map[largest]
			remainder = remainder - largest
		end
		return roman
	end
end
