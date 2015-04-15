class Phrase
	attr_reader :phrase
	def initialize(phrase)
		@phrase = phrase
	end
	def word_count
		count = Hash.new(0)
		phrase.downcase.scan(/[\w']+/).each {|word| count[word]+=1}
		count
	end

end
