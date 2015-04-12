class Phrase
	def initialize(words)
		words= words.delete(":,!&@$%^\"")
		@phrase=words.split(" ")

	end

	def word_count
		counts={}
		@phrase.each do| word |
			word=word.to_s
			word=word.downcase
			counts[word] ||= 1
			counts[word]+=1
	     end
		counts
	end
end
