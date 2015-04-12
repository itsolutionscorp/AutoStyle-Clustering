class Phrase
  def initialize phrase
    @phrase = phrase
  end
  
  def word_count
    count parse_words
  end
   
  private
  
  def parse_words
    @phrase.downcase.scan(/\w+/)
  end
  
  def count words
    words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1}
  end
end
