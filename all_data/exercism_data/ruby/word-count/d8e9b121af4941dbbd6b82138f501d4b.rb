class Phrase

  def initialize(phrase)
  	@phrase = phrase
  end

  def get_valid_words(phrase)
  	phrase.downcase.scan(/[a-z0-9]+/)
  end

  def word_count

  	words = get_valid_words(@phrase)
  	
  	words.inject(Hash.new(0)) do |result, element|
  	  result[element] += 1
  	  result
  	end

  end

end
