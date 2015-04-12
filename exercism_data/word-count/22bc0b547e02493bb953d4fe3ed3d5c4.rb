class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    return @word_count if @word_count
    
    @word_count = Hash.new(0)
    words.each {|w| @word_count[w] += 1}
    
    @word_count
  end
  
  private

  def words
    
    # remove punctuation
    processed_phrase = @phrase.gsub(/[^0-9a-z ,]/i, '')
    
    # remove list
    processed_phrase = processed_phrase.gsub(/[,]/, ' ')
    
    # lowercase everything
    processed_phrase = processed_phrase.downcase
    
    processed_phrase.split 
  end
  
  def count_words(words)
    @word_count = Hash.new(0)
    words.each {|w| @word_count[w] += 1}
  end
  
end