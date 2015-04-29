class Anagram < String
  def match(candidates)
    candidates.select { |candidate| anagrams?(candidate, self) }
  end

  def anagrams?(first_word, second_word)
    first_word.split('').sort == second_word.split('').sort
  end
end
