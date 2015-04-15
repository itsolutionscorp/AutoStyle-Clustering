#
# This module derives the RNA complement of DNA strands,
# and the DNA complement of RNA strands.
#
module Complement

  # Note the order of the bases: 'A' and 'U' are complements,
  # 'C' and 'G' are complements, etc.
  DNA_BASES = 'ACGT'
  RNA_BASES = 'UGCA'

  # Complement.of_dna('UGCACCAGAAUU') => 'ACGTGGTCTTAA'
  def self.of_dna(dna)
    dna.tr(DNA_BASES, RNA_BASES)
  end

  # Complement.of_rna('ACGTGGTCTTAA') => 'UGCACCAGAAUU'
  def self.of_rna(rna)
    rna.tr(RNA_BASES, DNA_BASES)
  end

end
