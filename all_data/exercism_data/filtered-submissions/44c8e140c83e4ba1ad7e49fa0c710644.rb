def compute(strand_a, strand_b)
    strand_a = strand_a.chars
    strand_b = strand_b.chars

    strand_a.zip(strand_b).count do |nucleotide_a, nucleotide_b|
      nucleotide_a != nucleotide_b
    end
  end