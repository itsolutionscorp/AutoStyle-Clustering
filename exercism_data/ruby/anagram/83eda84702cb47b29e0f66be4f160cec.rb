class Anagram
  def initialize subject
    @subject = subject
  end

  def match candidates
    candidates.select { |candidate| anagram? candidate }
  end

  private

  def anagram? candidate
    !eq?(candidate) && alphagram?(candidate)
  end

  def eq? candidate
    @subject.downcase == candidate.downcase
  end

  def alphagram? candidate
    sort_ordered(@subject.downcase) == sort_ordered(candidate.downcase)
  end

  def sort_ordered unsorted
    unsorted.chars.sort
  end
end
