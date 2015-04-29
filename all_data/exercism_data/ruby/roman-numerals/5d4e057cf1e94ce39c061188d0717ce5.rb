class Fixnum
	def to_roman
		num_array = self.to_s.split('').map(&:to_i)
		roman_str = Array.new

		translation_hash = {1 => ["I", "IV", "V", "IX"], 2 => ["X", "XL", "L", "XC"], 3 => ["C", "CD", "D", "CM"]}
		#figure out first digit
		i = 1

		until (i > num_array.length or i > 3)
			if num_array[-i].between?(1, 3) then
				roman_str << (translation_hash[i][0] * num_array[-i])
			elsif num_array[-i] == 4 then
				roman_str << (translation_hash[i][1])
			elsif num_array[-i] == 5 then
				roman_str << (translation_hash[i][2])
			elsif num_array[-i].between?(6, 8) then
				roman_str << ((translation_hash[i][2]) + (translation_hash[i][0] * (num_array[-i] - 5)))
			elsif num_array[-i] == 9 then
				roman_str << (translation_hash[i][3])
			end
			i = i + 1
		end

		if num_array.length > 3 then
			roman_str << ("M" * num_array[-4])
		end

		return roman_str.reverse.join("")
	end
end
