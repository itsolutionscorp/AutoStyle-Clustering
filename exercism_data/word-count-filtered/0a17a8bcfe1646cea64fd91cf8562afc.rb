class Phrase

	def initialize(phrase)
		@phrase = phrase 
	end

	def word_count
		words = @phrase.downcase.scan(/\w+/)
		word_hash = Hash.new(0) 
		words.each do |word|
			word_hash[word] += 1 
		end
		word_hash
	end

end 
