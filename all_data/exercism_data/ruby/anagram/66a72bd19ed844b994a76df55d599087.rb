class Anagram

	def initialize(base_word)
		@base_word = base_word.downcase
		@base_letter_sort = @base_word.chars.sort
	end

	def match(words_array)		
		words_array.each_with_object([]) do |word, anagrams|
			anagrams << word 		if anagram?(word.downcase)
		end
	end

	private

	def anagram?(word)
		not_identical(word) & equal_letter_sorts(word)
	end

	def not_identical(word)
		@base_word != word
	end

	def equal_letter_sorts(word)
	  @base_letter_sort == word.chars.sort 
	end

end

#the above code is much faster, but not as easily read possibly, 
#as the code below, which makes use of the "permutation" method.
=begin
class Anagram

	def initialize(base_word)
		@base_word = base_word.downcase
		@base_anagrams = @base_word.scan(/./).permutation.to_a.each_with_object([]) {|arr, sarr| sarr << arr.join}
	end
		
	def match(word_array)
    word_array = word_array.each_with_object([]){
    	|word, array|
    	if (@base_word != word.downcase) & @base_anagrams.include?(word.downcase)
    		array << word
    	end
    }
	end

end
	
=end
