#!/usr/bin/env ruby

class Phrase
	#initializes object and sets all words to lower case
	def initialize(input_phrase)
		@phrase = input_phrase.downcase
	end

	#returns the word_count_hash with the words and number of occurrences
	def word_count
		word_count_hash = count()
		return word_count_hash	
	end
	
	#counts the number of words in the input phrase and stores the word/occurrence pairs in a new hash
	def count
		count_hash = Hash.new(0)
		#count_hash = { 'word' => 1 }
		
		#removes any non-alphanumeric characters except apostrophe and splits into an array on whitespace
		frmttd_phrase = @phrase.gsub(/[^a-zA-Z0-9']/, ' ').split
		
		
		frmttd_phrase.each do |arr_word|
			if count_hash.empty?
				count_hash.store(arr_word, 1)
			elsif count_hash.has_key?(arr_word)
				count_hash[arr_word] += 1
			else
				count_hash.store(arr_word, 1)
			end
			
		end

		return count_hash
	end
	
end
