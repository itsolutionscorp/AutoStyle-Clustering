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
    chars_sort_ordered(@subject) == chars_sort_ordered(candidate)
  end

  def chars_sort_ordered unsorted_string
    unsorted_string.downcase.chars.sort.join
  end
end
