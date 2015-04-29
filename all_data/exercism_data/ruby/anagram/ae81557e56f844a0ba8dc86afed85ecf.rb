class Anagram
  def initialize(subject)
    @subject       = subject
    @subject_chars = chars(subject)
  end

  def match(candidates)
    candidates.select { |c| anagram?(c) }
  end

  private

  def anagram?(candidate)
    distinct?(candidate) && same_chars?(candidate)
  end

  def distinct?(candidate)
    candidate.casecmp(@subject) != 0
  end

  def same_chars?(candidate)
    chars(candidate) == @subject_chars
  end

  def chars(s)
    s.downcase.chars.sort
  end
end
