class Phrase
  attr_reader :phrase

  def initialize(phrase)
     @phrase = phrase.downcase
  end  

  def word_count
    words = phrase.scan(/\w+/)
    words.each_with_object(Hash.new(0)) { |str, hsh| hsh[str] += 1 }
  end
end
