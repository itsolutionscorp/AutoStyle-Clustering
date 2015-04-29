class Phrase
	attr_reader :word_count

	def initialize str
		@word_count = Hash.new(0)

		str.split(/[^'a-zA-Z0-9_]/).each{ |word|
			@word_count[word.downcase] += 1 unless word.size == 0
		}
	end
end
