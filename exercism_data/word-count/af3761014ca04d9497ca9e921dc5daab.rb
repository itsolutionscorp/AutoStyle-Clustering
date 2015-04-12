class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)
    words.each_with_object(counts) do | word, counts |
      counts[word] += 1 
    end
  end
 
  private
  
  def words
    @phrase.downcase.scan(/\w+/)
  end

end
