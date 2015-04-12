class Phrase

	def initialize(phrase)
		@phrase = phrase.to_s
	end

	def word_count
		@word_count ||= parse
	end

	private
	
	def parse
		@word_count = Hash.new(0)
		tokenize(normalize(@phrase)).each { |word|
			@word_count[word] += 1
		}
		@word_count
	end

	def normalize(str)
		str.downcase
	end

	def tokenize(str)
		str.scan(/\w+/)
	end
end
