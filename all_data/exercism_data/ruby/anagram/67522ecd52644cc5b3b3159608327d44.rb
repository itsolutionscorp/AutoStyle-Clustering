class Anagram
	def initialize(word)
		@sentence = word
	end


	def match(anagram)
		@answer = []
		anagram.each do |gram|
			if gram.length == @sentence.length
				sentence = letter_count(@sentence)
				a_gram = letter_count(gram)
				if sentence == a_gram
					@answer.push(gram)
				end
			end
		end
		@answer
	end

	def letter_count(word)
		word.split("").inject({}) do |count, letter|
			count[letter] ||= 0
			count[letter] += 1
			count
		end
	end


end
