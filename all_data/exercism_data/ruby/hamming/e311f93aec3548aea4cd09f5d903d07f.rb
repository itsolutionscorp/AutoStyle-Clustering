class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(other)
    pairs(other).count { |a, b| a != b }
  end

  private
  def pairs(other)
    sequence_of_size(other).zip other.chars
  end

  def sequence_of_size(other)
    @sequence.chars.take other.size
  end

  def size
    @sequence.size
  end
end
