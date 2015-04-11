class Anagram
  attr_reader :letters
  def initialize(word)
    @letters = sorted_chars(word)
  end

  def match(word_list)
    word_list.select { |word| anagram_of?(word) }
  end

  private
  def anagram_of?(word)
    letters == sorted_chars(word)
  end

  def sorted_chars(word)
    word.chars.sort
  end
end
