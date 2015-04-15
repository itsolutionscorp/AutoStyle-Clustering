class DNA
  def initialize strand
    @strand = strand
  end

  def hamming_distance other_strand
    other_nucleotides = other_strand.chars.to_a
    nucleotides_to_check = StrandComparator.common_length(other_nucleotides, nucleotides)

    nucleotides[0...nucleotides_to_check].each_with_index.reject do |nucleotide, index|
      other_nucleotides[index] == nucleotide
    end.length
  end

  private

  def strand
    @strand
  end

  def nucleotides
    @nucleotides ||= strand.chars.to_a
  end
end

class StrandComparator
  def self.common_length(strand1, strand2)
    [strand1.length, strand2.length].min
  end
end
