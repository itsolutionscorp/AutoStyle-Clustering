class Anagram
	def initialize(source_word)
		@source_word = source_word
	end

	def match(words)
		if not words.is_a?Array
			raise ArgumentError, "Value must be an Array."
		end
		my_words = words
		#groupings are done by position in array. so by making our source word the first element 
		#any groupings for source will be the first eelement back in the return array
		my_words.unshift(@source_word)
		result = my_words.group_by { |element| element.downcase.chars.sort }.values[0]
		
		if result.is_a?Array #we have a grouping
			result.shift #remove source word
			#filter out any duplicates that snuck
			return result.select{ |word| word.downcase != @source_word.downcase } 
		else
			#no groupings so return an empty array
			return []
		end
	end
end
