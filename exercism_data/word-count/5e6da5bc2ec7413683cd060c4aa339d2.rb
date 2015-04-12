class Phrase
  def initialize(phrase)
    @phrase = phrase        
  end
  
  def word_count
    count_words
  end

  private
  def count_words
    count = Hash.new(0)
    get_words.each do |word|      
      count[word] += 1
    end  
    count
  end
  
  def get_words
    @phrase.downcase.scan(/\w+/)
  end
  
end
