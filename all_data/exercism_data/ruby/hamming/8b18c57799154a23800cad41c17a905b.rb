class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(other_sequence)
    nucleotide_pairs(other_sequence).count do |(our_nucleotide, other_nucleotide)|
      our_nucleotide != other_nucleotide
    end
  end

  private

  def nucleotides(sequence)
    sequence.chars
  end

  def nucleotide_pairs(other_sequence)
    minimum_sequence_length = [@sequence, other_sequence].map(&:length).min
    nucleotides(@sequence).zip(nucleotides(other_sequence))[0...minimum_sequence_length]
  end
end
