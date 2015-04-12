class Hamming
  def compute(dna_strand_1, dna_strand_2)
    fail ArgumentError, 'strands are not equal size' unless \
      dna_strand_1.length == dna_strand_2.length
    first_strand = dna_strand_1.chars
    second_strand = dna_strand_2.chars
    first_strand.zip(second_strand).count { |a, b| a != b }
  end
end
