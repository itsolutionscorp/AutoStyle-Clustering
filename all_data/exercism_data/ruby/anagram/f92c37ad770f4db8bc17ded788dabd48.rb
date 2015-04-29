class Anagram
  def initialize(word)
    @word = normalize_word(word)
  end

  def match(possible_anagrams)
    possible_anagrams.select do |possible_anagram|
      is_anagram?(normalize_word(possible_anagram))
    end
  end

  private

  def is_anagram?(other_word)
    !is_identical?(other_word) && has_same_characters?(other_word)
  end

  def is_identical?(other_word)
    word == other_word
  end

  def has_same_characters?(other_word)
    sorted_characters(word) == sorted_characters(other_word)
  end

  def sorted_characters(word)
    word.chars.to_a.sort
  end

  def normalize_word(word)
    word.downcase
  end

  attr_accessor :word
end
