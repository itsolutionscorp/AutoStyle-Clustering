class Phrase

	def initialize(phrase)
		@phrase = normalize(phrase)
	end

	def word_count
		@word_count ||= parse
	end

	private
	
	def parse
		@word_count = Hash.new(0)
		@phrase.split.each do |word| 
			@word_count[word] += 1
		end
		@word_count
	end

	def normalize (phrase)
		phrase.to_s.downcase.gsub(/\W+/, ' ')
	end
end
