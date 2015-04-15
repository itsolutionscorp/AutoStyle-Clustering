class Anagram

  def initialize(word)
    @given_word = word
  end

  def match(possible_anagrams)
    possible_anagrams.select do |word|
      is_different?(word) && is_anagram?(word)
    end
  end

  private

  def is_different?(possible_anagram)
    possible_anagram.downcase != @given_word.downcase
  end

  def is_anagram?(possible_anagram)
    sort(possible_anagram) == sort(@given_word)
  end

  def sort(word)
    word.downcase.chars.sort
  end

end
