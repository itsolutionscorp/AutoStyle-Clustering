class Hamming

  def self.compute(seq_a, seq_b)
    Strand.new(seq_a).points_of_mutation(Strand.new(seq_b)).length
  end

end

class Strand

  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence.chars
  end

  def length
    sequence.length
  end

  def [](index)
    sequence[index]
  end

  def points_of_mutation(strand)
    (0...length).select do |index|
      strand[index] && self[index] != strand[index]
    end
  end

  def to_s
    sequence.join
  end

end
