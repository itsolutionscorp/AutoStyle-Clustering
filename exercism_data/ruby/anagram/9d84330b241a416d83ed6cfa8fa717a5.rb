class Anagram
  attr_reader :text
  private :text

  def initialize(text)
    @text = text
  end

  def match(words)
    words.select(&is_anagram?)
  end

  private

  def is_anagram?
    text_letters = word_to_letters(text)

    lambda do |word|
      word_letters = word_to_letters(word)

      return false if same_word?(word_letters, text_letters)
      return true if same_word?(word_letters.sort, text_letters.sort)
    end
  end

  def word_to_letters(word)
    word.downcase.scan(/./)
  end

  def same_word?(word, another_word)
    word == another_word
  end
end
