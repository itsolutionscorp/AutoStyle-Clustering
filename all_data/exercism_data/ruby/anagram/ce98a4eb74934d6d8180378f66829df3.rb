class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    return @word_count if @word_count
    
    count_words(words)
        
    @word_count
  end
  
  private

  def words
    # remove punctuation, remove list, lowercase everything.
    @phrase.gsub(/[^0-9a-z ,]/i, '').gsub(/[,]/, ' ').downcase.split
  end
  
  def count_words(words)
    @word_count = Hash.new(0)
    words.each {|w| @word_count[w] += 1}
  end
  
end
