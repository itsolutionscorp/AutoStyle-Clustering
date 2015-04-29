#NOTE: just for fun, take it easy)
class Anagram
  def initialize(sample)
    @sample = sample
  end

  # yes, it charifies and sorts sample every time...
  def anagram?(candidate, sample)
    return false unless candidate.length == sample.length
    candidate_chars = candidate.downcase.chars
    sample_chars = sample.downcase.chars
    candidate_chars != sample_chars &&
      candidate_chars.sort == sample_chars.sort
  end

  def match(anagram_candidates)
    anagram_candidates.select { |candidate| anagram? candidate, @sample }
  end
end
