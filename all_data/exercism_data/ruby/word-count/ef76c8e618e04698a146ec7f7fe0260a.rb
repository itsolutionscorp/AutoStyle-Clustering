class Phrase

	def initialize(phrase)
		@phrase = phrase 
	end

	def word_count
		words = @phrase.gsub(/\W+/," ").downcase.split 
		wf = Hash.new(0) 
		words.each do |word|
			wf[word] += 1 
		end
		wf
	end

end 
