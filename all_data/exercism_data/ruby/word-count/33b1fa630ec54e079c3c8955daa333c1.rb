class Phrase

	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
		organize_string
		convert_string_to_hash
	end

	def organize_string
		@phrase.downcase!
		@phrase.gsub!(",", " ")
		@phrase.gsub!(/\p{^Alnum}/, ' ')
		@phrase_to_array = @phrase.split(" ")
	end

	def convert_string_to_hash
		counts = Hash.new(0)
		@phrase_to_array.each { |word| counts[word] += 1 }
	  counts = Hash[counts] 
	end
end
