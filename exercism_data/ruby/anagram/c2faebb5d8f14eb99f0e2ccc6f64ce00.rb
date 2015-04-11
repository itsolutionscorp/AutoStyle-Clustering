class Anagram
  def initialize(word)
    @analyzer = Analyzer.new(base_word: word)
  end

  def match(words)
    words.select do |word|
      @analyzer.match? word
    end
  end

  class Analyzer
    attr_reader :base_word

    def initialize(options = {})
      @base_word = options[:base_word]
      @base_word.downcase! unless @base_word.nil?
    end

    def match?(another_word)
      !@base_word.nil? && different_words?(another_word) && same_chars?(another_word)
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
