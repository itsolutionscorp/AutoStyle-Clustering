class Phrase
	attr_reader:word_count
	def initialize(w)
		@word_count = {}
		w.downcase.gsub(/[^a-z0-9\']/, " ").split(/[\s,]+/).each do |w|
			@word_count[w] = (@word_count[w] || 0)+1
		end
	end
end
