class Phrase

	attr_reader :word_count

	def initialize(words)
		@word_count = {}
		words.downcase.gsub(/[^a-z0-9.]+/, ' ').squeeze(' ').split.each do |w|
			c = @word_count[w]
			@word_count[w] = c.nil? ? 1 : c+1 
		end
	end

end
