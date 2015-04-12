def compute(str1, str2)
		distance = 0
		str2_chars = str2.chars.to_a
		str1.chars.each_with_index do |e, i|
			distance += 1 if e != str2_chars[i]
		end
		distance
	end