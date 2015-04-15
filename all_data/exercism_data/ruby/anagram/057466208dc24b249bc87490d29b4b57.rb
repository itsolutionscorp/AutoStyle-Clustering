class Anagram
  def initialize subject
    @subject = subject
  end

  def match candidates
    candidates.select { |candidate| anagram? candidate }
  end

  private

  def anagram? candidate
    !duplicate?(candidate) && alphagram?(candidate)
  end

  def duplicate? candidate
    @subject.casecmp(candidate).zero?
  end

  def alphagram? candidate
    chars_sort_ordered(@subject) == chars_sort_ordered(candidate)
  end

  def chars_sort_ordered unsorted_string
    unsorted_string.downcase.chars.sort.join
  end
end
