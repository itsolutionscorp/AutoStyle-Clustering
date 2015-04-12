class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @words = phrase.downcase.scan(/[\w]+/)
  end

  def word_count
    @words.each_with_object(Hash.new(0)){|word,count| count[word]+=1 }      
  end
end
