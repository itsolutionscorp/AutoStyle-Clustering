class Phrase
  def initialize(phrase)
    @phrase = phrase.to_s
  end
  
  def word_count
    words.each_with_object(Hash.new(0)) { |word, hsh| hsh[word] += 1 }
  end 
  
  protected
  
  def words
    @phrase.downcase.split(/\W+/)
  end
  
end
