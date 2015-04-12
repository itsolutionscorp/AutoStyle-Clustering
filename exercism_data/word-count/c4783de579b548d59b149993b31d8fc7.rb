class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)
    get_list_of_words.each_with_object(counts) do | a, h |
      h[a] += 1 
    end
      
  end
 
  private
  
  def get_list_of_words
    @phrase.downcase.scan(/\w+/)
  end

end
