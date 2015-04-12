class Phrase
	def initialize word
		@word = word
	end
	def word_count
		words = @word.split(%r{[^'a-zA-Z0-9]})
		words.reject!(&:empty?)
		words.each_with_object(Hash.new(0)) {|word, counter| counter[word.downcase]+=1}
	end
end
