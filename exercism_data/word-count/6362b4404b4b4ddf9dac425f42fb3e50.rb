class Phrase
	def initialize(word)
		@word = word
	end

	def word_count
		@word.downcase.split(/[^\w']+/).reduce(Hash.new) { |h, w| h.update(w => h.fetch(w, 0) + 1) }
	end
end
