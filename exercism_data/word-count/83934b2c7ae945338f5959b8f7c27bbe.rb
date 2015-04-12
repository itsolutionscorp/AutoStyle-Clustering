class Phrase
  def initialize(phrase)
    @phrase = phrase        
  end
  
  def word_count
    @count ||= count_words
  end

  private
  def count_words
    count = Hash.new(0)
    words.each do |word|      
      count[word] += 1
    end  
    count
  end
  
  def words
    @phrase.downcase.scan(/\w+/)
  end
  
end
