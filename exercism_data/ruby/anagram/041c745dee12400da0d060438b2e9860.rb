class Anagram
  def initialize(subject)
    @subject = subject
  end

  def match(candidates)
    candidates.select { |candidate| anagram?(candidate) }
  end

  private

  def anagram?(candidate)
    distinct_from_subject?(candidate) && same_letters_as_subject?(candidate)
  end

  def distinct_from_subject?(candidate)
    candidate.downcase != @subject.downcase
  end

  def same_letters_as_subject?(candidate)
    letters(candidate) == letters(@subject)
  end

  def letters(word)
    word.downcase.chars.sort
  end
end
