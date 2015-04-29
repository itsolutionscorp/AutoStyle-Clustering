class DNA
  def initialize(dna_string)
    @dna_string = dna_string
  end

  def hamming_distance(other_dna_string)
    if dna_string.length <= other_dna_string.length
      count_different_nucleotides(dna_string, other_dna_string)
    else
      count_different_nucleotides(other_dna_string, dna_string)
    end
  end

  private

  def count_different_nucleotides(shorter_strand, longer_strand)
    shorter_strand.chars.with_index.count do |nucleotide, index|
      nucleotide != longer_strand[index]
    end
  end
  attr_reader :dna_string
end
