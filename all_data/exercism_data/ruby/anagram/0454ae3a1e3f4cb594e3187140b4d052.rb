class Anagram
  def initialize word
    @word = word
    @downcase_word = @word.downcase
    @sorted_downcase_word = @downcase_word.sort
  end

  def match candidates
    candidates.select { |candidate| anagram?(candidate.downcase) }
  end

  def anagram?(candidate)
    (@downcase_word != candidate) && (@sorted_downcase_word == candidate.sort)
  end

end

class String
  def sort
    chars.sort.join
  end
end
