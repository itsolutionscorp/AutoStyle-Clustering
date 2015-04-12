def compute(str1, str2)
		# set initial hamming distance variable
		hamming_distance = 0

		# combine the two strings into an array containing each character using zip
		str1.chars.zip(str2.chars).each {
			|str1_char, str2_char|
			# compare to see if the values match and iterate the distance if they do
			hamming_distance += 1 if str1_char != str2_char
		}
		# output hamming distance
		hamming_distance
	end