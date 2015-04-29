class DNA
  def initialize strand
    @strand = strand
  end

  def hamming_distance mutation
    strand.chars.zip(mutation.chars).count do |nucleotide, mutated_nucleotide|
      mutated_nucleotide && mutated_nucleotide != nucleotide
    end
  end

  private

  attr_reader :strand
end
