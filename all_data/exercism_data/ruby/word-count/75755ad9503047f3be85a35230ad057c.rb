class Phrase
  
  attr_reader :content, :words, :count
  def initialize(content)
    @content = StringHelper.normalize_case(
                  StringHelper.replace_punctuation_with_spaces(content)
                )
    @words = StringHelper.split_words_on_spaces(@content)
    @count = {}
  end
  
  def initialize_count!
    words.each do |word|
      count[word] = 0
    end
  end
  
  def word_count
    initialize_count!
    
    words.each do |word|
      count[word] += 1
    end
    
    return count   
  end
end

class StringHelper
  def self.normalize_case(string)
    string.downcase
  end
  
  def self.replace_punctuation_with_spaces(string)
    string.gsub(/[^a-zA-Z0-9\s]/, ' ')
  end
  
  def self.split_words_on_spaces(string)
    string.split(' ')
  end
end
