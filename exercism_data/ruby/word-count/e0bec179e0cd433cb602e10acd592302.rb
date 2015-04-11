class Phrase
  
  def initialize(phrase)
    @words = Hash.new
    
    phrase.downcase!
    remove_punctuation!(phrase)
    remove_list!(phrase)
    
    phrase.split.each do |word|
      if @words.has_key?(word)
        @words[word] += 1
      else
        @words[word] = 1
      end
    end
  end
  
  def word_count
    @words
  end
  
  private # all methods that follow are private: not accessible from outside 
  
  def remove_punctuation!(phrase)
    phrase.gsub!(/[^0-9a-z ,]/i, '')
  end
  
  def remove_list!(phrase)
    phrase.gsub!(/[,]/, ' ')
  end
end
