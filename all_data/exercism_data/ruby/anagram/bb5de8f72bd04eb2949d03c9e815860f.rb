class Anagram < String

  def match(possible_anagrams)
    possible_anagrams.delete_if { |str| not anagram_of?(str) }
  end

  def anagram_of?(other)
    Anagram.key_of(self) == Anagram.key_of(other) &&
      downcase != other.downcase
  end

  def Anagram.key_of(str)
    str.downcase.chars.sort
  end
  
end
