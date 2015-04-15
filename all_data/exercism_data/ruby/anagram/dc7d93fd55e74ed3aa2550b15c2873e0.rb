class Anagram
	def initialize(anagram)
		@amount_of_letters=anagram.size
		@anagram_sort= anagram.chars.sort.join
	end

	def match(gram)
		result=[]
		gram.each do |word|
			if word.size==@amount_of_letters
				word_sort=word.chars.sort.join
				if @anagram_sort==word_sort
					result.push(word)
				end
			end
		end
		return result
	end
end
