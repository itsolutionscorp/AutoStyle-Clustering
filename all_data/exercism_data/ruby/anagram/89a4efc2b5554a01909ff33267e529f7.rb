class Anagram
  attr_accessor :reference_word

  def initialize(reference_word)
    @reference_word = reference_word
  end

  def match(potential_anagrams)
    potential_anagrams.select { |word| anagram?(word) }
  end

  private

  def anagram?(word)
    canonicalize(word) == canonicalized_reference
  end

  def canonicalize(word)
    word.chars.sort
  end

  def canonicalized_reference
    @canonicalized_reference ||= canonicalize(reference_word)
  end
end
