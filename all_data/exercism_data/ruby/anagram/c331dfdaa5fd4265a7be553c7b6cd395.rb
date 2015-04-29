class Anagram < Struct.new(:word)
  
  def match candidates
    candidates.select { |candidate| anagram? candidate }
  end

  private

  def anagram? candidate
    candidate.characters == word.downcase.characters
  end

end

class String

  def characters
    self.chars.sort
  end

end
