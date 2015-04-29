class Anagram < Struct.new(:word)
  def match candidates
    candidates.select { |candidate| anagram_of_word? candidate }
  end

  private

  def anagram_of_word? candidate
    candidate.alphabetic == word.alphabetic
  end
end

class String
  def alphabetic
    self.downcase.chars.sort
  end
end
