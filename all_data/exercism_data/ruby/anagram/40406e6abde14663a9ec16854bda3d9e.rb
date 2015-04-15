class Anagram
  attr_reader :source_word
  def initialize(root_word)
    @source_word = Anagram.new(root_word)
  end
  
  def match(test_words)
    test_words.select{ |word| source_word.anagram_of?(word) }
  end
  
  private
  
  class Anagram
    attr_reader :split_word

    def initialize(source_word)
      @split_word = source_word.downcase.split(//).sort
    end
    
    def anagram_of?(word)
      (split_word.size == word.size) && letters_match?(word.downcase)
    end
    
    def letters_match?(word)
      word.split(//).sort == split_word
    end
  end
end
