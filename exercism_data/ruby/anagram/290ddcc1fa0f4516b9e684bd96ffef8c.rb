class Anagram
  attr_reader :letters
  def initialize(letters)
    @letters = rearrange_chars(letters)
  end

  def match(word_list)
    word_list.select { |word|
      anagram?(word)
    }
  end

  private
  def anagram?(word)
    rearrange_chars(word) == letters
  end

  def rearrange_chars(word)
    word.split('').sort
  end
end
