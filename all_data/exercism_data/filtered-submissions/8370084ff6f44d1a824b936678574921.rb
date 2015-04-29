def compute(str1, str2)

		hamming_distance = 0


		str1.chars.zip(str2.chars).each {
			|str1_char, str2_char|

			hamming_distance += 1 if str1_char != str2_char
		}

		hamming_distance
	end