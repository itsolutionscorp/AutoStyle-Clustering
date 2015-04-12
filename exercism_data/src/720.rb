class Phrase
	def initialize(phrase)
		@phrase = phrase.chomp.downcase.gsub(/\W+/, ' ').split
	end

	def word_count
		word_array = @phrase
		counts = Hash.new(0)

		if counts.empty?
			word_array.each { |word| counts[word] += 1 }
		end

		return counts
	end
end
