class Phrase
  
  def initialize(phrase)
    @phrase = phrase
  end
  
  def word_count
    word_list = extract_words(@phrase)
    word_list.each_with_object(Hash.new(0)) do |word,word_counts|
      word_counts[word] += 1 
    end
  end

  private

  def extract_words(phrase)
    phrase.downcase.scan(/[a-z0-9']+/) 
  end
  
end
