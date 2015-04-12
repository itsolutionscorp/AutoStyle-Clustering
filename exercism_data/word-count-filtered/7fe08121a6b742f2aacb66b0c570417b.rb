class Phrase
	def initialize(words)
		words= words.delete(":,!&@$%^\"")
		@phrase=words.split(" ")

	end

	def word_count
		counts={}
		@phrase.each do| word |
			word=word.to_s.downcase
			counts[word] ||= 0
			counts[word]+=1
	     end
		counts
	end
end
