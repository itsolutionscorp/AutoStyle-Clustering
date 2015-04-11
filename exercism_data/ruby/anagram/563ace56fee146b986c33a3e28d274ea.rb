class Anagram
  attr_reader :word, :potential_anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(potential_anagrams)
    potential_anagrams.select &matches?
  end

  private

  def matches?
    -> (potential_anagram) { @potential_anagram = potential_anagram.downcase; anagram? }
  end

  def anagram?
    !identical? && same_letters?
  end

  def same_letters?
    word.chars.sort == potential_anagram.chars.sort
  end

  def identical?
    word == potential_anagram
  end
end
