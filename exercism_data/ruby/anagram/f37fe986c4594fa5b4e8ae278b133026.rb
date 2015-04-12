class Anagram
  def initialize subject
    @subject = subject
  end

  def match candidates
    candidates.reject { |candidate| duplicate? candidate }
              .select { |candidate| anagram? candidate }
  end

  private

  def duplicate? candidate
    @subject.casecmp(candidate) == 0
  end

  def anagram? candidate
    alphabetically_sorted_string(@subject) == alphabetically_sorted_string(candidate)
  end

  def alphabetically_sorted_string unsorted_string
    unsorted_string.downcase.chars.sort.join
  end
end