class Anagram
  attr_reader :source_word
  def initialize(root_word)
    @source_word = String(root_word).extend(AnagramHelper)
  end
  
  def match(test_words)
    test_words.select{ |word| source_word.anagram_of?(word) }
  end
  
  private
  
  module AnagramHelper
    attr_reader :normalized_source
    
    def anagram_of?(word)
      self.letters_match?(word)
    end
    
    def letters_match?(word)
      normalize(word) == normalized_source
    end
    
    def normalize(word)
      word.downcase.chars.sort
    end
    
    def normalized_source
      @normalized_source ||= normalize(self)
    end
  end
end
