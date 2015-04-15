class Phrase
  
  def initialize(phrase)
    @phrase = phrase
  end
  
  def word_count
    normalized_word_list.each_with_object(Hash.new(0)) do |word,word_counts|
      word_counts[word] += 1 
    end
  end

  private

  def normalized_word_list
    @phrase.downcase.scan(/[a-z0-9']+/) 
  end
  
end
