class Phrase
  attr_reader :phrase

	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
		array_of_words(@phrase).each_with_object(Hash.new(0)){
			|word, hash| 
			hash[word] += 1 if word != "'"  #elminate any words exactly == apostrophe
		} 
	end

	def array_of_words(phrase) 
	  #gsub keeps apostrophes, commas, and spaces
	  #next gsub eliminates any groups of 2 or more apostrophes
	  #then downcase
	  #then split on commas and spaces and any groups thereof
	  #....heaven forbid two commas in a row...that's going too far !!
	  #note resulting array could contain words of apostrophes only
		phrase.gsub(/[^a-zA-Z0-9',\s]/,"").gsub(/''+/,"").downcase.split(/\s*,\s*|\s+/)		
	end
	
end
