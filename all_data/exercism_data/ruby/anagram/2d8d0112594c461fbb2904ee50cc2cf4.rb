class Anagram

	def initialize(anagram)
		@anagram = anagram.downcase
		@sorted_anagram = alphabetize(anagram)
	end

	def match(matched_words)
		matched_words = remove_identical_words(matched_words)
		matched_words.select { |word| alphabetize(word).eql?(@sorted_anagram) }
	end

	private

	def remove_identical_words(array)
		array.delete_if {|word| word.downcase == @anagram }
	end

	def alphabetize(string)
		string.downcase.chars.sort.join
	end

end
