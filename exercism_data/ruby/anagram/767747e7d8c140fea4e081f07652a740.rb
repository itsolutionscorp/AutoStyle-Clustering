class Anagram
	def initialize(word)
		@word=word
	end

	def match(array)
		results = Array.new()
		sorted_word = @word.upcase.split(//).sort.join("")

		array.each { |s|
			candidate_word = s.upcase.split(//).sort.join
			if sorted_word == candidate_word && @word.upcase != s.upcase
				results.push(s)
			end
		}
		return results
	end
end
