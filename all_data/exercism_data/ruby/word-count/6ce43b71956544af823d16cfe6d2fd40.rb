class Phrase
  
  def initialize(phrase)
    
    phrase.downcase!
    remove_punctuation!(phrase)
    remove_list!(phrase)
    
    @words = Hash.new do |hash, key| 
      phrase.split.each do |word|
        if @words.has_key?(word)
          hash[key] += 1
        else
          hash[key] = 1
        end
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
