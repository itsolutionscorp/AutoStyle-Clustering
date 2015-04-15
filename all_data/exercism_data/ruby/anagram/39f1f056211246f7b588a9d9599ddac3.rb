class Anagram

  def initialize(sample)
    @subject = sample.downcase
  end

  def match(candidates)
    candidates.find_all{ |candidate| anagram? candidate.downcase }
  end

  private

  def anagram?(candidate)
    same_alphagram?(candidate) && different_word?(candidate)
  end

  def same_alphagram?(candidate)
    @subject.chars.sort == candidate.chars.sort
  end

  def different_word?(candidate)
    @subject != candidate
  end

end
