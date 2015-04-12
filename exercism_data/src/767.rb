class Phrase

	def initialize(text)
		@text = text
	end

	def word_count
		words = @text.downcase.split(/\W+/) # Split on non-word (letter, number, underscore)
		counts = {}
		words.each do |word|
			counts[word] ||= 0
			counts[word] += 1
		end
		counts
	end

end
