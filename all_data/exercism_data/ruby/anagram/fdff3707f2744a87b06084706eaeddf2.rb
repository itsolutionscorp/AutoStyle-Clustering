class Anagram
  attr_reader :word
  def initialize(word)
    @word = word.downcase
  end

  def match(potential_anagrams)
    potential_anagrams.select do |potential_anagram|
      has_same_letters?(potential_anagram) unless is_same_word?(potential_anagram)
    end
  end

  private

  def is_same_word?(potential_anagram)
    word == potential_anagram.downcase
  end

  def has_same_letters?(potential_anagram)
    word.chars.sort == potential_anagram.downcase.chars.sort
  end
end
