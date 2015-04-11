class Hamming
  def self.compute(strand1, strand2)
    compare_differences(paired_nucleotides(normalized_strands(strand1, strand2)))
  end

  private

  def self.normalized_strands(strand1, strand2)
    length_of_shortest_strand = [strand1, strand2].min.length
    cut_to_equal_lengths(strand1, strand2, length_of_shortest_strand)
  end

  def self.cut_to_equal_lengths(strand1, strand2, length)
    [strand1[0, length], strand2[0, length]]
  end

  def self.paired_nucleotides(array_of_strands)
    strand1 = array_of_strands.first.chars
    strand2 = array_of_strands.last.chars

    strand1.zip(strand2)
  end

  def self.compare_differences(nucleotide_pairs)
    nucleotide_pairs.count { |array| array[0] != array[1] }
  end
end
