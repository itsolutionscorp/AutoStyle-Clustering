class Hamming

  def self.compute(strand_one, strand_two)
    paired_strands(strand_one, strand_two).count do |nucleotide_one, nucleotide_two|
      mutation?(nucleotide_one, nucleotide_two)
    end
  end

  def self.paired_strands(strand_one, strand_two)
    strand_one_nucleotides = strand_one.chars
    strand_two_nucleotides = strand_two.chars

    strand_one_nucleotides.zip(strand_two_nucleotides)
  end

  def self.mutation?(nucleotide_one, nucleotide_two)
    !(nucleotide_one.nil? || nucleotide_two.nil?) && (nucleotide_one != nucleotide_two)
  end
end
