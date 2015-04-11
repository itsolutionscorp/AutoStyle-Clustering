class Anagram
  def initialize word
    @analyzer = Analyzer.new word
  end

  def match words
    words.select do |word|
      @analyzer.match? word
    end
  end

  class Analyzer
    def match? another_word
      diffent_words?(another_word) && same_chars?(another_word)
    end

    def initialize base_word
      @base_word = base_word.downcase
    end

    private
    def diffent_words? another_word
      @base_word != another_word.downcase
    end

    def same_chars? another_word
      another_word = another_word.downcase
      @base_word.chars.sort == another_word.chars.sort
    end
  end
end
