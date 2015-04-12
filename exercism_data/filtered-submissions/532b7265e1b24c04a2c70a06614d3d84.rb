module Hamming
  LEFTOVER_CHARS = -> (pair) { pair.any?(&:nil?) }
  DIFFERENT_PAIRS = -> (pair) { pair[0] != pair[1] }

  def compute(first, second)
    first.chars
      .zip(second.chars)
      .reject(&LEFTOVER_CHARS)
      .count(&DIFFERENT_PAIRS)
  end
end
