require 'pry'

class Anagram
  def initialize(word)
    @word = word
  end

  def match(anagrams)
    anagrams.select{|anagram| is_anagram?(anagram) }
  end

  private
  def is_anagram?(anagram)
    normalize(anagram) == normalize(@word) &&
    anagram.upcase != @word.upcase
  end

  def normalize(word)
    word.upcase.split('').sort
  end

end
