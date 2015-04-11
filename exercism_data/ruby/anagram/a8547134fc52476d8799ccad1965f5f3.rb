class Anagram

	def initialize(base_word)
		@base_word = base_word.downcase
		@base_letter_frequencies = letter_frequencies(@base_word)	
	end

	def match(words_array)		
		words_array.each_with_object([]) do |word, anagrams|
			anagrams << word if anagram?(word.downcase)
		end
	end

	def anagram?(word)
		if @base_word == word
			false
		else
			@base_letter_frequencies == letter_frequencies(word)
		end
	end

	def letter_frequencies(word)
		word.strip.scan(/./).each_with_object(Hash.new(0)) { |ch, let_freq|
			let_freq[ch].nil? ? let_freq[ch] = 1 : let_freq[ch] += 1}		
	end

end
