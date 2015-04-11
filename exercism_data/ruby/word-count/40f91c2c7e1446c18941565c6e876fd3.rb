class Phrase

  def initialize(phrase)

    @phrase = phrase
     
  end

  def word_count
    if (@words == nil)
      @words = Hash.new(0)
    
      process_phrase_for_word_count
      count_words    
      
    end
    
    @words
    
  end

  private # all methods that follow are private: not accessible from outside 

  def process_phrase_for_word_count
    # remove punctuation
    @phrase.gsub!(/[^0-9a-z ,]/i, '')
    
    # remove list
    @phrase.gsub!(/[,]/, ' ')
    
    # lowercase everything
    @phrase.downcase!
    
  end
  
  def count_words
    @phrase.split.each {|w| @words[w] += 1}
  end
  
end
