class Phrase
	def initialize(sentence)
		separator = " "
		@sentence = sentence.downcase.gsub(/[^0-9A-Za-z]/, separator).split(separator).sort
		@result_map = Hash.new
	end

	def word_count
		word = @sentence[0]
		counter = 0

		@sentence.each do |next_word|
			if next_word == word
				counter += 1
			else
				word = next_word
				counter = 1
			end
			@result_map[word] = counter
		end
		puts @result_map
		@result_map
	end

end
