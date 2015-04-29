class Integer
	def to_roman
		romansI = { 1 => "I", 2 => "II", 3 => "III" , 4 => "IV", 5 => "V", 6 => "VI", 7 => "VII", 8 => "VIII", 9 => "IX" }
		romansII = { 1 => "X", 2 => "XX", 3 => "XXX", 4 => "XL", 5 => "L", 6 => "LX", 7 => "LXX", 8 => "LXXX", 9 => "XC" }
		romansIII =  { 1 => "C", 2 => "CC", 3 => "CCC", 4 => "CD", 5 => "D", 6=> "DC", 7 => "DCC", 8 => "DCCC", 9 => "CM" }
		romansIIII = { 1 => "M", 2 => "MM", 3 => "MMM" }
		number = []
		number = self.to_s.split('')

		if number.length == 1
			return romansI[self]
		end

		if number.length == 2
			return romansII[number[0].to_i] + romansI[number[1].to_i] unless number.include?('0')
			return romansII[number[0].to_i]
		end

		if number.length == 3
			return romansIII[number[0].to_i] + romansII[number[1].to_i] + romansI[number[2].to_i] unless number.include?('0')
			return romansIII[number[0].to_i] if number[1] == '0' && number[2] == '0'
			return romansIII[number[0].to_i] + romansII[number[1].to_i] if number[2] == '0'
			return romansIII[number[0].to_i] + romansI[number[2].to_i]
		end

		if number.length == 4
			return romansIIII[number[0].to_i] + romansIII[number[1].to_i] + romansII[number[2].to_i] + romansI[number[3].to_i] unless number.include?('0')

			return romansIIII[number[0].to_i] if number[1] == '0' && number[2] == '0' && number[3] == '0'

			return romansIIII[number[0].to_i] + romansIII[number[1].to_i] if number[2] == '0' && number[3] == '0'
			return romansIIII[number[0].to_i] + romansII[number[2].to_i] if number[1] == '0' && number[3] == '0'
			return romansIIII[number[0].to_i] + romansI[number[3].to_i] if number[1] == '0' && number[2] == '0'

			return romansIIII[number[0].to_i] + romansII[number[2].to_i] + romansI[number[3].to_i] if number[1] == '0'
			return romansIIII[number[0].to_i] + romansIII[number[1].to_i] + romansI[number[3].to_i] if number[2] == '0'
			return romansIIII[number[0].to_i] + romansIII[number[1].to_i] + romansII[number[2].to_i] if number[3] == '0'
		end
	end
end
