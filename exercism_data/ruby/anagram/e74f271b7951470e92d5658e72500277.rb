class Anagram

	def initialize(anagram)
		@anagram = anagram
		@alphabetized_anagram = alphabetize(anagram)
	end

	def match(value)
		value = remove_identical_words(value)
		value.select { |word| alphabetize(word).casecmp(@alphabetized_anagram) == 0 }
	end

	private

	def remove_identical_words(array)
		array.delete_if {|word| word.downcase == @anagram.downcase }
	end

	def alphabetize(string)
		string.downcase.chars.sort.join
	end

end
