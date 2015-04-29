class Anagram
	def initialize(word)
		@sentence = word
	end


	def match(anagram)
		anagram.inject([]) do |answer, gram|
			if gram.length == @sentence.length
				sentence = letter_count(@sentence)
				a_gram = letter_count(gram)
				
				if sentence == a_gram
					answer.push(gram)
				end
			end
			answer	
		end 
	end

	def letter_count(word)
		word.split("").inject({}) do |count, letter|
			count[letter] ||= 0
			count[letter] += 1
			count
		end
	end


end
