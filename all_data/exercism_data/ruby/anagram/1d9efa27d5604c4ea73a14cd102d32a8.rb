class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(other_words)
    other_words.find_all { |other_word| Comparison.new(word, other_word).anagram? }
  end

  class Comparison
    attr_reader :word, :other_word

    def initialize(word, other_word)
      @word, @other_word = word.downcase, other_word.downcase
    end

    def anagram?
      !identical? && same_letters?
    end

    private

    def same_letters?
      word.chars.sort == other_word.chars.sort
    end

    def identical?
      word == other_word
    end
  end
end
