class Hamming
  attr_reader :sequence

  def self.compute(sequence, other_sequence)
    Hamming.new(sequence).distance(Hamming.new(other_sequence))
  end

  def initialize(sequence)
    @sequence = sequence.scan(/\w/)
  end

  def distance(other)
    @sequence.zip(other.sequence).select {|(a, b)| a != b }.size
  end
end
