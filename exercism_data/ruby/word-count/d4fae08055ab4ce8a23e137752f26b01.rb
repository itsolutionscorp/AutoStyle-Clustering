class Phrase
  
  def initialize phrase
    @string = String(phrase)
  end
  
  def word_count
    words.each_with_object(Hash.new(0)) do |word, accumulator| 
      accumulator[word] += 1
    end
  end
  
  private
  
  def words
    @string.downcase.split(/\W+/)
  end
  
end
