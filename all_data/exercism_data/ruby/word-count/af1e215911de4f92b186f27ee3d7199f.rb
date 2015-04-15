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
    @count = {}
    words.each do |word|
      @count[word] = @count[word] ? (@count[word] + 1) : 1
    end
    @count
  end
end
