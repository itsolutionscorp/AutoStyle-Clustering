class Anagram

  attr_accessor :word

  def initialize(word)
    @word = word
  end

  def match(possible_anagrams)
    possible_anagrams.select { |word| anagram?(word) }
  end

  private
  def anagram?(possible_anagram)
    same_letters?(word, possible_anagram) && different?(word, possible_anagram)
  end

  def same_letters?(word1, word2)
    word1.downcase.chars.sort == word2.downcase.chars.sort
  end

  def different?(word1, word2)
    word1.downcase != word2.downcase
  end
end
