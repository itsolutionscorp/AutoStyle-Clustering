class Anagram

  def initialize(word)
    @given_word = word
  end

  def match(possible_anagrams)
    possible_anagrams.select do |possible_anagram|
      is_different?(possible_anagram) && is_anagram?(possible_anagram)
    end
  end

  private

  def is_different?(possible_anagram)
    possible_anagram.downcase != @given_word
  end

  def is_anagram?(possible_anagram)
    sort(possible_anagram) == sort(@given_word)
  end

  def sort(word)
    word.downcase.chars.sort
  end

end
