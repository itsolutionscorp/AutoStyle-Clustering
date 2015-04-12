class Phrase
  
  attr_reader :words
  def initialize(content)
    @words = content.
                downcase.
                split_words_on_non_word_characters
                
  end

  def word_count
    count = Hash.new(0)
    
    words.each do |word|
      count[word] += 1
    end
    
    return count   
  end
end

class String  
  def replace_punctuation_with_spaces
    gsub(/[^a-zA-Z0-9\s]/, ' ')
  end
  
  def split_words_on_non_word_characters
    split(/\W+/)
  end
end