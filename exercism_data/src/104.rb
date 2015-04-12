class Phrase
  attr_reader :phrase

  def initialize(phrase)
     @phrase = phrase.downcase
  end  

  def word_count
    dictionary = Hash.new(0)
    phrase.scan(/\w+/) {|word| dictionary[word] += 1} 
    
    dictionary 
  end
end
