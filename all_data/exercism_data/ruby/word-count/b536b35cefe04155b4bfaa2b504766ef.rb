class Phrase
	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
		characters = Hash.new(0)
		words.each_with_object(characters) do |word, list|
      		list[word] += 1
    	end
	end

	def words
      input = @phrase.downcase.split(" ") 
      #to remove special characters
      array_of_words = input.each { |s| s.gsub! /\p{^Alnum}/, '' }
	end
end
