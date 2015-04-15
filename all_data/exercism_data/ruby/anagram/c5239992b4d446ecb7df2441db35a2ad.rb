class Anagram
  attr_accessor :alphabetized_source

  def initialize(source)
    @alphabetized_source = alphabetize(source)
  end

  def match(potential_anagrams)
    potential_anagrams.select { |word| anagram?(word) }
  end

  private

  def anagram?(word)
    alphabetize(word) == alphabetized_source
  end

  def alphabetize(word)
    word.chars.sort
  end
end
