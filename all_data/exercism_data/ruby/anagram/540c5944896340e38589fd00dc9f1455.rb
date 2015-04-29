class Anagram
  
  def initialize(word)
    @word = word.downcase
  end

  def match(possible_anagrams)
    possible_anagrams.select { |possible_anagram|  possible_anagram.anagram_of?(@word) }
  end

end

class String
  
  def anagram_of?(other_word)
    self != other_word && self.sort == other_word.sort
  end

  def sort
    self.chars.sort.join
  end

end
