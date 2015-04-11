class Anagram
  attr_reader :alphabetized_word

  def initialize(word)
    @alphabetized_word = alphabetize(word)
  end

  def match(possible_anagrams)
    possible_anagrams.select do |word|
      alphabetized_word == alphabetize(word)
    end
  end

  private

  def alphabetize(word)
    word.downcase.chars.sort
  end
end
