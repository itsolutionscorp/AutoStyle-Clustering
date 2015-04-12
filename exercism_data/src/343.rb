class Phrase
	def initialize phrase
		@phrase = phrase
	end


	def word_count
		words = {}
		@phrase.split(/[^\w']/).reject(&:empty?).map(&:downcase).each do |w|
			words[w] = 0 if words[w].nil?
			words[w] += 1
		end
		return words
	end
end
