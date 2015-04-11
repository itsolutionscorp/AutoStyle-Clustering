class Anagram < Struct.new(:word)
  def match candidates
    candidates.select { |candidate| anagram? candidate }
  end

  private

  def anagram? candidate
    candidate.chars.sort == word.downcase.chars.sort
  end

end
