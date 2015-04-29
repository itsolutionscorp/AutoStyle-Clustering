class Anagram

  def initialize(needle)
    @needle = needle.downcase
  end

  def match(haystack)
    haystack.select do |candidate|
      anagram_with? candidate.downcase
    end
  end

  private

  attr_reader :needle

  def anagram_with?(candidate)
    different_word?(candidate) && same_letters?(candidate)
  end

  def different_word?(candidate)
    needle != candidate
  end

  def same_letters?(candidate)
    letters(needle) == letters(candidate)
  end

  def letters(word)
    word.chars.sort
  end

end
