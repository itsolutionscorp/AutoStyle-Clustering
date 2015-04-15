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
    word = @word.downcase
    anagram = possible_anagram.downcase

    word.chars.sort == anagram.chars.sort && word != anagram
  end
end
