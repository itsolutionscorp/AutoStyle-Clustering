class Anagram < String

  def match(possible_anagrams)
    possible_anagrams.select do |possible_anagram|
      sort_and_downcase(possible_anagram) == sort_and_downcase(self)
    end 
  end

  private

  def sort_and_downcase(str)
    str.downcase.chars.sort
  end

end
