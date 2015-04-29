class Candidate

  def self.different?(word, candidate)
    word.downcase != candidate.downcase
  end

  def self.same_letters?(word, candidate)
    sort_letters(word) == sort_letters(candidate)
  end

  private

  def self.sort_letters(word)
    word.downcase.chars.sort
  end

end

class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.find_all { |candidate| anagram?(candidate) }
  end

  private

  def anagram?(candidate)
    Candidate.same_letters?(word,candidate) && Candidate.different?(word,candidate)
  end

end
