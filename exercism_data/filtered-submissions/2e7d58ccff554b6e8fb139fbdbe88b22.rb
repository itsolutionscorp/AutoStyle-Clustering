class Hamming
  def compute(strand, other_strand)
    nucleotide_mapping = strand.chars.zip(other_strand.chars)
    hamming_distance = nucleotide_mapping.count do |nucleotide, other_nucleotide|
      nucleotide != other_nucleotide
    end
  end
end
