class Phrase
  def initialize(phrase)
    @phrase = phrase        
  end
  
  def word_count
    count_words
  end

  private
  def count_words
    word_count = Hash.new(0)
    get_words.each do |word|      
      word_count[word] += 1
    end  
    word_count
  end
  
  def get_words
    @phrase.downcase.scan(/\w+/)
  end
  
end
