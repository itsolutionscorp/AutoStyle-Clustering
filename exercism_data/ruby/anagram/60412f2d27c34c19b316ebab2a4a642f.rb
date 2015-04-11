class Anagram
  def initialize(word)
    @word = word
  end

  def match(possible_anagrams)
    return possible_anagrams.select { |possible| anagram?(possible) }
  end

  private
  def anagram?(possible)
    original, other = @word.downcase, possible.downcase
    return original != other && original.chars.sort == other.chars.sort
  end
end
