class DNA
  attr_reader :sequence

  def initialize(strand)
    @sequence = to_sequence(strand)
  end

  def hamming_distance(other)
    other_sequence = to_sequence(other)
    sequence.zip(other_sequence).count do |pair|
      mutated?(pair)
    end
  end

  private

    def mutated?(pair)
      original, other  = pair
      other && original != other
    end

    def to_sequence(strand)
      strand.chars
    end
end
