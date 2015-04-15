class Anagram
  attr_reader :letters
  def initialize(word)
    @letters = rearrange_letters(word)
  end

  def match(word_list)
    word_list.select { |word|
      anagram?(word)
    }
  end

  private
  def anagram?(word)
    letters == rearrange_letters(word)
  end

  def rearrange_letters(word)
    word.split('').sort
  end
end
