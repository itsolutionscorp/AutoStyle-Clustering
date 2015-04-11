class Hamming
  def self.compute(strand, other)
    normalized_pairs(strand, other).count &method(:mutated?)
  end

  def self.to_sequence(strand, length)
    strand.chars.take(length)
  end

  def self.normalized_pairs(strand, other)
    sequence = to_sequence(strand, other.length)
    other_sequence = to_sequence(other, strand.length)
    sequence.zip(other_sequence)
  end

  def self.mutated?(pair)
    original, other = pair
    original != other
  end

end
