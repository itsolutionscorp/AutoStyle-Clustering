class Anagram
  attr_reader :word
  def initialize word
    @word = word.downcase
  end

  def match words
    words.select { |a_word|
      anagram_of?(a_word.downcase)
    }
  end

  protected
  def sorted_chars a_word = word
    a_word.downcase.chars.sort
  end

  def anagram_of? other
    other != word && sorted_chars == sorted_chars(other)
  end
end
