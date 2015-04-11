class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject.downcase
  end

  def match(list)
    list.select do |candidate|
       WordMatch.new(subject, candidate).anagram?
    end
  end
end


class WordMatch
  attr_reader :subject, :candidate

  def initialize(subject, candidate)
    @subject, @candidate = subject, candidate
  end

  def anagram?
    !exact_word? && sorted_match?
  end

  private

  def sorted_match?
    array_sort(subject) == array_sort(candidate)
  end

  def array_sort(word)
    word.chars.sort
  end

  def exact_word?
    subject == candidate
  end
end
