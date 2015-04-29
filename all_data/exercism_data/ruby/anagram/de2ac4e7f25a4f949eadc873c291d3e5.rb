class Anagram

  def initialize(needle)
    @needle = needle
  end

  def match(haystack)
    haystack.select(&method(:anagram_with?))
  end

  private

  attr_reader :needle

  def anagram_with?(candidate)
    different_word?(candidate) && same_letters?(candidate)
  end

  def different_word?(candidate)
    needle.downcase != candidate.downcase
  end

  def same_letters?(candidate)
    letters(needle) == letters(candidate)
  end

  def letters(word)
    word.downcase.chars.sort
  end

end
