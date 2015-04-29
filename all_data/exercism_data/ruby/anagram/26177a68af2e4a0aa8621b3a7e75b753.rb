class Anagram

  def initialize(subject)
    @subject = subject.downcase
  end

  def match(candidates)
    filter_by_letters(candidates - [@subject])
  end

  private

  def filter_by_letters candidates
    letters_in_subject = letters_in @subject
    candidates.select {|cand| letters_in_subject == letters_in(cand) }
  end

  def letters_in word
    word.chars.sort
  end

end
