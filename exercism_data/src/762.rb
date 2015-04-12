class Phrase
  attr_reader :phrase

	def initialize(phrase)
		@phrase = phrase
	end

	#count words in a phrase, output format: {"word1" => 3, "word2" => 1, ....}
	#no punctuation or non-word letters get included - numbers are included here
	#underlines(although not specified by exercism) and apostrophe's are included
	def word_count
		@phrase.scan(/([\w']+)/).flatten.each_with_object(Hash.new(0)){
			|word, hash| 
			hash[word.downcase] += 1 
		} 
	end

end
