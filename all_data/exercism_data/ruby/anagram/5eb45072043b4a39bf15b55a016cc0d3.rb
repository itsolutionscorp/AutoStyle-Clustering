class Anagram < String

  def match(possible_anagrams)
    possible_anagrams.delete_if do |str| 
      not Anagram.new(str).anagram_of?(self)
    end
  end

  def anagram_of?(other)
    anagram_key == other.anagram_key &&
      downcase != other.downcase
  end

  def anagram_key
    downcase.chars.sort.join
  end
  
end
