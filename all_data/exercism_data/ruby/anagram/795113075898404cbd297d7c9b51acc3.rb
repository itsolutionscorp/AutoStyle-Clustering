class Anagram
  attr_reader :letters
  def initialize(word)
    @letters = chars_sorted(word)
  end

  def match(word_list)
    word_list.select { |word| anagram_of?(word) }
  end

  private
  def anagram_of?(word)
    letters == chars_sorted(word)
  end

  def chars_sorted(word)
    word.chars.sort
  end
end
