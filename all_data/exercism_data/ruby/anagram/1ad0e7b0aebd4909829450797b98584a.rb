class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(other_words_to_test)
    other_words_to_test.
      select { |other_word| anagram_of?(other_word) }
  end

  private

  def anagram_of?(other_word)
    raw_letters(word) == raw_letters(other_word)
  end

  def raw_letters(word)
    word.strip.downcase.chars.sort
  end
end
