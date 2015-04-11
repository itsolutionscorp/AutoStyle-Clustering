class Hamming
  def self.compute(strand_a, strand_b)
    new(strand_a, strand_b).number_of_nucleotide_differences
  end

  def initialize(strand_a, strand_b)
    @strand_a, @strand_b = strand_a, strand_b
  end

  def number_of_nucleotide_differences
    paired_strands_of_equal_length.count do |nuc_a, nuc_b|
      nuc_a != nuc_b
    end
  end

  private

  def paired_strands_of_equal_length
    nucs_a, nucs_b = strands_of_equal_length
    nucs_a.zip(nucs_b)
  end

  def strands_of_equal_length
    [nucleotides_a.take(shorter_strand_length),
     nucleotides_b.take(shorter_strand_length)]
  end

  def nucleotides_a
    @strand_a.chars
  end

  def nucleotides_b
    @strand_b.chars
  end

  def shorter_strand_length
    [@strand_a.size, @strand_b.size].min
  end
end
