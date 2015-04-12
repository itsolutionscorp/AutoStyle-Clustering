class Phrase

	def initialize(input)
		@phrase = input 
	end

	def word_count
		word_collection = Hash.new(0)
		std_phrase = normalize(@phrase)
		std_phrase.scan(/\w+/) do |word|
			word_collection[word] += 1
	  end	
		word_collection	
	end
	
	private

	def normalize(orig_phrase)
		orig_phrase.downcase!
		orig_phrase	
	end

end
