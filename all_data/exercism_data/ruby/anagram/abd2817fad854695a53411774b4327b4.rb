class Anagram
	def initialize(word)
		@word=word
	end

	def match(array)
		results = Array.new()
		sorted_word = @word.upcase.chars.sort

		array.each { |s|
			candidate_word = s.upcase.chars.sort
			if sorted_word == candidate_word && @word.upcase != s.upcase
				results.push(s)
			end
		}
		return results
	end
end
