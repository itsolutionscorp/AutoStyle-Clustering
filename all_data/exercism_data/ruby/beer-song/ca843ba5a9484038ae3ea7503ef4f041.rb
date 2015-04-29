class Anagram < String

  def match(possible_anagrams)
    possible_anagrams.select do |possible_anagram|
      normalize(possible_anagram) == normalize(self)
    end 
  end

  private

  def normalize(str)
    str.downcase.chars.sort
  end

end
