def compute(str1, str2)

		hamming_distance = 0


		short, long = [str1, str2].sort


		long.chars.zip(short.chars).each {
			|long_char, short_char|

			hamming_distance += 1 if long_char != short_char
		}

		hamming_distance
	end