class Anagram
  def initialize(sample)
    @sample = sample
  end

  def match(candidates)
    @sample_chars = @sample.downcase.chars
    @sample_sorted_chars = @sample_chars.sort
    candidates.select { |candidate| anagram? candidate }
  end

  private

  def anagram?(candidate)
    return false unless candidate.length == @sample.length
    candidate_chars = candidate.downcase.chars
    candidate_chars != @sample_chars &&
      candidate_chars.sort == @sample_sorted_chars
  end
end
