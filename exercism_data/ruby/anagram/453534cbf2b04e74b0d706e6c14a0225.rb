class Anagram

	def initialize(base_word)
		@base_word = base_word.downcase
		@base_letter_counts = letter_counts(@base_word)	
	end

	def match(words_array)		
		words_array.each_with_object([]) do |word, anagrams|
			anagrams << word 		if anagram?(word.downcase)
		end
	end

	private

	def anagram?(word)
		not_identical(word) & equal_letter_counts(word)
	end

  def not_identical(word)
  		@base_word != word
	end

	def equal_letter_counts(word)
		@base_letter_counts == letter_counts(word)
	end

	def letter_counts(word)
		#output is a hash of the count of each letter in the word: {"a" => 2, "g" = 1,......}
		#array_of_word_letters(word).each_with_object(Hash.new(0)) { |ch, let_freq|
		#	let_freq[ch].nil? ? let_freq[ch] = 1 : let_freq[ch] += 1}		
		#hash_of_letter_counts(array_of_word_letters(word))	
		hash_of_letter_counts(array_of_word_letters(word))	
	end

  def hash_of_letter_counts(letter_array)
  	letter_array.each_with_object(Hash.new(0)) { |ch, let_count|
			let_count[ch].nil? ? let_count[ch] = 1 : let_count[ch] += 1}
  end

  def array_of_word_letters(word)
		word.strip.scan(/./)
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
