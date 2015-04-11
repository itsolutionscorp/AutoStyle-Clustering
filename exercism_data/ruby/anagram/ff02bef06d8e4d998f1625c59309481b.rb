class Anagram
  def initialize(word)
    @word = word
  end

  def match(other_words)
    other_words.find_all { |other_word| PotentialAnagramComparison.new(word, other_word).anagram? }
  end

  private

  attr_reader :word

  class PotentialAnagramComparison
    def initialize(word, potential_anagram)
      @word, @potential_anagram = word, potential_anagram
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

    def word
      @word.downcase
    end

    def other_word
      @potential_anagram.downcase
    end
  end
end
