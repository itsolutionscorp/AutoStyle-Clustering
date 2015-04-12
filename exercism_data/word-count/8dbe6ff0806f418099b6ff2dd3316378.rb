class Phrase
  def initialize phrase
    @phrase = phrase
  end
  
  def word_count
    count parse_words
  end
   
  private
  
  def parse_words
    @phrase.downcase.scan(/\w+/).collect
  end
  
  def count words
    words.each_with_object({}) { |word, hash| hash[word] = hash[word] ? (hash[word] + 1) : 1}
  end
end
