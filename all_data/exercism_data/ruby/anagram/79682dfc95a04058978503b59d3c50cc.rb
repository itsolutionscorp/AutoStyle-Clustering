class String

  def anagram_of?(other_string)
    anagram_key == other_string.anagram_key &&
      downcase != other_string.downcase
  end

  def anagram_key
    downcase.chars.sort.join
  end
  
end

class Anagram < String

  def match(possible_anagrams)
    possible_anagrams.delete_if { |str| not str.anagram_of?(self) }
  end

end
