class Anagram
  attr_reader :letters
  def initialize(word)
    @letters = rearrange_letters(word)
  end

  def match(word_list)
    word_list.select { |word|
      anagram_of?(word)
    }
  end

  private
  def anagram_of?(word)
    letters == rearrange_letters(word)
  end

  def rearrange_letters(word)
    word.chars.sort
  end
end
