class Phrase
  
  attr_accessor :phrase
  
  def initialize(phrase)
    @phrase = phrase
  end
  
  def word_count
    count_words(split_into_words(normalize_case(phrase)))
  end
  
private
  
  def normalize_case phrase
    phrase.downcase
  end
  
  def split_into_words normaized_phrase
    normaized_phrase.scan(/\w+/)
  end
  
  def count_words words
    words.each_with_object({}) do |word, accumulator|
      accumulator[word] = (accumulator[word] || 0) + 1
    end
  end
  
end
