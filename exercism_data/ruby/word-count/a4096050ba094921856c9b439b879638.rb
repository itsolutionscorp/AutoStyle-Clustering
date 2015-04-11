class Phrase
  
  attr_accessor :raw_word_list
  
  def initialize(raw_sentence)
    @raw_word_list = clean_invalid_characters(raw_sentence).split
  end
  
  def word_count
    word_counter = {}
    return word_counter unless raw_word_list
    
    raw_word_list.each do |raw_word|
      word = raw_word.downcase
      current_word_count = word_counter[word] || 0
      word_counter[word] = current_word_count + 1
    end
    
    word_counter
  end
  
private
    
  def clean_invalid_characters(text)
    return '' if text.nil?
    text.gsub(/\W/,' ')
  end
end
