class Phrase
  
  def initialize(phrase)
    @phrase = phrase
    @words = phrase.downcase.scan(/\w+/)
  end
  
  def word_count
    @words.each_with_object(Hash.new(0)) {|word, freqs| freqs[word] += 1 }
  end

end
