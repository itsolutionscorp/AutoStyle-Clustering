class Phrase
	def initialize(sentence)
		@sentence = sentence
	end

	def word_count
		counts = Hash.new(0)
		@sentence = @sentence.gsub(/[^a-zA-Z,[0-9]' ]/,'').gsub(/ +/,' ')
		@sentence = @sentence.gsub(/,/,' ').gsub(/ +/,' ')
		
		  words = @sentence.split(' ') 
        
		
		words.each do |word|
		  word = word.downcase
		  counts[word] =  counts[word] + 1
	    end


	    return counts

	end


end
