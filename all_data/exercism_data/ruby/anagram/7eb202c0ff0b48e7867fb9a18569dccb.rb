class Anagram
  attr_reader :text_letters
  private :text_letters

  def initialize(text)
    @text_letters = to_letters(text)
  end

  def match(words)
    words.select { |word| is_valid_anagram?(word) }
  end

  private

  def is_valid_anagram?(word)
    word_letters = to_letters(word)

    return false if exactly_same_word?(word_letters, text_letters)
    return true if same_letters?(word_letters, text_letters)
  end

  def to_letters(word)
    word.downcase.chars
  end

  def same_letters?(letters, other_letters)
    letters.sort == other_letters.sort
  end

  def exactly_same_word?(word, another_word)
    word == another_word
  end
end
