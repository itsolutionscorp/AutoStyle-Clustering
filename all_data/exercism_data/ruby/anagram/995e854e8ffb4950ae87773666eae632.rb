class Anagram

  def initialize(subject)
    @subject = subject.downcase
  end

  def match(candidates)
    filter_by_letters filter_by_checksum distinct candidates
  end

  private

  def distinct candidates
    candidates - [@subject]
  end

  def filter_by_checksum candidates
    subject_sum = @subject.sum
    candidates.select {|cand| cand.sum == subject_sum }
  end

  def filter_by_letters candidates
    letters_in_subject = letters_in @subject
    candidates.select {|cand| letters_in_subject == letters_in(cand) }
  end

  def letters_in word
    word.split(//).sort
  end

end
