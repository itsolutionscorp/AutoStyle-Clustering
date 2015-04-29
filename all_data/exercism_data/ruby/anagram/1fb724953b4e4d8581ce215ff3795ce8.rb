class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(other_words)
    other_words.find_all { |other_word| comparison.against(other_word).anagram? }
  end

  private

  def comparison
    @comparison ||= AnagramComparison.new(word)
  end

  class AnagramComparison
    attr_reader :word, :other_word

    def initialize(word)
      @word = word.downcase
    end

    def against(other_word)
      @other_word = other_word.downcase
      self
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
