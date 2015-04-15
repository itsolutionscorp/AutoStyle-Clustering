class Anagram
  def initialize(word)
    @word = word.downcase
    @chars = char_group_for(@word)
  end

  def match(anagrams)
    normalize(anagrams).select{ |anagram| char_group_for(anagram) == chars }
  end

  private
  attr_reader :chars, :word

  def char_group_for(word)
    word.chars.sort
  end

  def normalize(anagrams)
    anagrams.map(&:downcase).reject{ |a| a == word }
  end

end
