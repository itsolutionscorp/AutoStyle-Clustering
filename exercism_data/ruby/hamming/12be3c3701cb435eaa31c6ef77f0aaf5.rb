class DNA
  def initialize(dna_strand)
    @dna_strand = dna_strand
  end

  def hamming_distance(other_dna_strand)
    comparable_indices(other_dna_strand).count do |index|
      nucleotides_at_index_differ?(index, other_dna_strand)
    end
  end

  private

  def nucleotides_at_index_differ?(index, other_dna_strand)
    dna_strand[index] != other_dna_strand[index]
  end

  def comparable_indices(other_dna_strand)
    (0...shorter_strand_length(other_dna_strand))
  end

  def shorter_strand_length(other_dna_strand)
    [dna_strand.length, other_dna_strand.length].min
  end

  attr_reader :dna_strand
end
