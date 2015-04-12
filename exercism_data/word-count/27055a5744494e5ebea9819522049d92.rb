class Phrase
	attr_reader :input
	def initialize(input)
		@phrase = input.downcase().gsub(/[^0-9A-Za-z']/, ' ').split(' ')
	end
	def word_count
  	counts = Hash.new(0)
   	@phrase.each do |word|
    	counts[word] += 1
	 	end
	return counts
	end
end
