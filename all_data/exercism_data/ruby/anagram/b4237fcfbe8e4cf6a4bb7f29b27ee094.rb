class Anagram

  def initialize(subject)
    @subject = subject.downcase
  end

  def match(candidates)
    elide_subject_from anagrams_in candidates
  end

  private

  def elide_subject_from candidates
    candidates - [@subject]
  end

  def anagrams_in candidates
    candidates.select {|candidate| has_same_letters_as_subject?(candidate) }
  end

  def has_same_letters_as_subject? candidate
    letters_in(candidate) == letters_in_subject
  end

  def letters_in_subject
    @letters_in_subject ||= letters_in @subject
  end

  def letters_in word
    word.chars.sort
  end

end
