class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other)
    normalized_pairs(other).count do |pair|
      mutated?(pair)
    end
  end

  private

    def mutated?(pair)
      original, other  = pair
      original != other
    end

    def to_sequence(strand, length)
      strand[0...length].chars
    end

    def normalized_pairs(other)
      sequence = to_sequence(strand, other.length)
      other_sequence = to_sequence(other, strand.length)
      sequence.zip(other_sequence)
    end

end
