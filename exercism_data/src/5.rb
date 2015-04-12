class Phrase
	def initialize word
		@word = word
	end
	def word_count
		counter = Hash.new
		words = @word.split(%r{[^'a-zA-Z0-9]})
		words.reject!(&:empty?)
		words.each do |word|
			word=word.downcase
			if counter.has_key?(word)
				counter[word]+=1
			else
				counter[word]=1
			end
		end
		counter
	end
end
