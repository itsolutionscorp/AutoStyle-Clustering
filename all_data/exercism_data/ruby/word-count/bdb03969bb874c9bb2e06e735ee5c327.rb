class Phrase
  attr_reader :phrase 

  def initialize(phrase)
    @phrase = phrase.downcase.split(/[^0-9a-z']+/)
  end

  def word_count
  	tallies = Hash.new(0)
  	phrase.each { |word| tallies[word] += 1}
  	tallies	
  end

end
