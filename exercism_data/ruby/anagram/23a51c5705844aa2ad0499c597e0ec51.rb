class Anagram
  def initialize(word)
    @analyzer = Analyzer.use_as_base(word)
  end

  def match(words)
    words.select do |word|
      @analyzer.match? word
    end
  end

  class Analyzer
    attr_accessor :base_word

    def self.use_as_base(word)
      analyzer = Analyzer.new
      analyzer.base_word = word.downcase
      analyzer
    end

    def match?(another_word)
      different_words?(another_word) && same_chars?(another_word)
    end

    private
    def different_words?(another_word)
      base_word != another_word.downcase
    end

    def same_chars?(another_word)
      another_word = another_word.downcase
      base_word.chars.sort == another_word.chars.sort
    end
  end
end
