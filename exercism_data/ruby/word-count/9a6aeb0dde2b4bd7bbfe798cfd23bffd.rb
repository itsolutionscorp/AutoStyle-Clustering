class Phrase
  
  def initialize(phrase)
    @phrase = phrase
  end
  
  def word_count
    words = words(@phrase)
    words.each_with_object(Hash.new(0)) {|word, freqs| freqs[word] += 1 }
  end
  
  private
  
  def words(phrase)
    phrase.downcase.scan(/\w+/)
  end

end