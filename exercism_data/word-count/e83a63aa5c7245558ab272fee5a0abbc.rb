class Phrase
  
  attr_accessor :words
  
  def initialize(sentence)
    @words = clean_invalid_characters(sentence).split
  end
  
  def word_count
    word_counter = {}
    return word_counter unless words
    
    words.each do |word|
      normalized_word = word.downcase
      if word_counter[normalized_word]
        word_counter[normalized_word] += 1
      else
        word_counter[normalized_word] = 1
      end
    end
    word_counter
  end
  
private
    
  def clean_invalid_characters(raw_text)
    return '' if raw_text.nil?
    raw_text.gsub(/\W/,' ')
  end
end
