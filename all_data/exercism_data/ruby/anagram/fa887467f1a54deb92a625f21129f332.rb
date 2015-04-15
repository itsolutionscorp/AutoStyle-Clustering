class Anagram
  def initialize subject
    @subject = subject
  end

  def match candidates
    reject_duplicates(candidates).select { |candidate| anagram? candidate }
  end

  private

  def reject_duplicates candidates
    candidates.reject { |candidate| exact_subject_match? candidate }
  end

  def anagram? candidate
    alphabetically_sorted_string(@subject) == alphabetically_sorted_string(candidate)
  end

  def exact_subject_match? candidate
    @subject.casecmp(candidate) == 0
  end

  def alphabetically_sorted_string unsorted_string
    unsorted_string.downcase.chars.sort.join
  end
end
