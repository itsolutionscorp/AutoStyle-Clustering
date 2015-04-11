class DNA
  def initialize strand
    @strand = strand
  end

  def hamming_distance other_strand
    comparator.hamming_distance strand, other_strand
  end

  private

  def strand
    @strand
  end

  def comparator
    @comparator ||= StrandComparator.new
  end
end

class StrandComparator

  def hamming_distance strand1, strand2
    strand1_nucleotides = nucleotides strand1
    strand2_nucleotides = nucleotides strand2
    common_strand_length = common_length(strand1_nucleotides, strand2_nucleotides)
    slice(strand1_nucleotides, common_strand_length).zip(strand2_nucleotides).count do |nucleotide1, nucleotide2|
      nucleotide1 != nucleotide2
    end
  end

  def common_length(strand1, strand2)
    [strand1.length, strand2.length].min
  end

  private

  def nucleotides strand
    strand.chars.to_a
  end

  def slice(nucleotides, length)
    nucleotides[0...length]
  end

end
