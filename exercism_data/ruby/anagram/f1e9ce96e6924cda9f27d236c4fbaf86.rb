class Anagram
  attr_reader :source_word
  def initialize(root_word)
    @source_word = AnagramSource.new(root_word)
  end
  
  def match(test_words)
    test_words.select{ |word| source_word.anagram_of?(word) }
  end
  
  private
  
  class AnagramSource
    attr_reader :normalized_word

    def initialize(source_word)
      @normalized_word = normalize(source_word)
    end
    
    def anagram_of?(word)
      letters_match?(word)
    end
    
    def letters_match?(word)
      normalize(word) == normalized_word
    end
    
    def normalize(word)
      word.downcase.chars.sort
    end
  end
end
