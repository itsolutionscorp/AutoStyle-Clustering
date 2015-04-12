class Phrase

	def initialize(input)
		@phrase = input 
	end

	def word_count
		word_collection = Hash.new
		word_collection.default = 0
		std_phrase = normalize(@phrase)
		std_phrase.scan(/\w+/) { |word|
			word_collection[word] += 1
		}
		word_collection	
	end
	
	private

	def normalize(orig_phrase)
		orig_phrase.downcase!
		orig_phrase	
	end

end
