class Anagram
  def initialize(input)
    @input = input
  end

  def match(possible_anagrams = [])
    possible_anagrams.select { |w| anagram_of?(w) }
  end

  private

  def anagram_of?(word)
    !identical_to?(word) && same_letters?(word)
  end

  def identical_to?(word)
    @input.casecmp(word) == 0
  end

  def normalized_letters(word)
    word.to_s.downcase.chars.sort.join
  end

  def same_letters?(word)
    normalized_letters(@input) == normalized_letters(word)
  end

end
