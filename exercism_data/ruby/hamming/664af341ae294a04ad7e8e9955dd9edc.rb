class Hamming
	def self.compute(str1, str2)
		# set initial hamming distance variable
		hamming_distance = 0

		# sort strings by size
		short, long = [str1, str2].sort

		# combine the two strings into an array containing each character using zip
		long.chars.zip(short.chars).each {
			|long_char, short_char|
			# compare to see if the values match and iterate the distance if they do
			hamming_distance += 1 if long_char != short_char
		}
		# output hamming distance
		hamming_distance
	end
end
