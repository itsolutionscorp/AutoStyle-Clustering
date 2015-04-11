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
    alphabetize(word) == alphabetized_reference
  end

  def alphabetize(word)
    word.chars.sort
  end

  def alphabetized_reference
    @alphabetized_reference ||= alphabetize(reference_word)
  end
end
