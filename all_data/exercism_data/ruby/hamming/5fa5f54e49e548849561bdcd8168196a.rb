class Hamming
	def self.compute(string1, string2)
		strings = [string1, string2]
		sorted_strings = sort_by_length(strings)
		zipped_chars = zip_strings(sorted_strings)
		chars_equal_ary = zipped_chars.map { |char_pair| char_pair[0] == char_pair[1] }
		chars_equal_ary.count { |equal| equal == false }
	end

	def self.zip_strings(strings)
		strings[0].chars.zip(strings[1].chars)
	end
	def self.sort_by_length(strings)
		strings.sort_by { |word| word.length }
	end
end
