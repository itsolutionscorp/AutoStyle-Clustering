class Phrase
  
  attr_reader :words
  def initialize(content)
    @words = content.
                downcase.
                replace_punctuation_with_spaces.
                split_words_on_spaces
                
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
  
  def split_words_on_spaces
    split(' ')
  end
end
