class Phrase

  def initialize(phrase)

    remove_punctuation!(phrase)
    remove_list!(phrase)

    @words = Hash.new(0)
    
    phrase.downcase.split.each {|w| @words[w] += 1}
    
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
