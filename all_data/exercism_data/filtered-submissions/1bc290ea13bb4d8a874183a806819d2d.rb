def compute(dna_fiber, other_dna_fiber)
    dna_fiber.chars.zip(other_dna_fiber.chars).count { |nucleotide_pair|
      nucleotide_pair.first != nucleotide_pair.last
    }