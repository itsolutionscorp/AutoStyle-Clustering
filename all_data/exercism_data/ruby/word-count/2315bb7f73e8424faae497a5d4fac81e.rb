class Phrase

	def initialize(input)
		@phrase = input 
	end

	def word_count
		word_collection = Hash.new(0)
		@phrase.downcase.scan(/\w+/) do |word|
			word_collection[word] += 1
	  end	
		word_collection	
	end

end
