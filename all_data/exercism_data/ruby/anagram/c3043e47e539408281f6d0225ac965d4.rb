class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(possible_anagrams)
    possible_anagrams.select { |possible_anagram| anagram?(possible_anagram.downcase) }
  end

  private

  def anagram?(possible_anagram)
    return false if @word == possible_anagram 
    return has_the_same_letters?(possible_anagram)
  end

  def has_the_same_letters?(possible_anagram)
    @word.chars.sort == possible_anagram.chars.sort
  end
end
