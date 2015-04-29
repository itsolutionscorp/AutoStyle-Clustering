def compute(dna_strand_1, dna_strand_2)

    dna_strands = dna_strand_1.chars.zip(dna_strand_2.chars)

    dna_strands.count do |nucleotide_1, nucleotide_2|
      nucleotide_1 != nucleotide_2
    end
  end