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
			if counts[word].nil?
				counts[word]=1
			else
				counts[word]+=1
			end
		
	end
		counts
	end
end
