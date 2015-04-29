class Anagram
  def initialize(input)
    @input = input
  end

  def match(possible_anagrams = [])
    possible_anagrams.select { |w| anagram_of?(w) }
  end

  private

  attr_reader :input

  def anagram_of?(word)
    !same_word_as?(word) && same_letters_as?(word)
  end

  def normalized_letters(word)
    word.to_s.downcase.chars.sort
  end

  def same_word_as?(word)
    input.casecmp(word) == 0
  end

  def same_letters_as?(word)
    normalized_letters(input) == normalized_letters(word)
  end

end
