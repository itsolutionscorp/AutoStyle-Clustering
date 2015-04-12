class Phrase
  def initialize phrase
    @phrase = phrase
  end
  
  def word_count
    word_count = Hash.new(0)
    
    words.each do |word|
      word_count[word] += 1
    end
    
    word_count
  end
  
  private
  
  def words
    @phrase.downcase.scan(/\w+/)
  end
end
