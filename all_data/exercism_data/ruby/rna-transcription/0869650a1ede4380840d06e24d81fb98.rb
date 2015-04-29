module Complement
  @rna_nuc = 'CGAU'
  @dna_nuc = 'GCTA'

  # @param [String] dna
  def self.of_dna(dna)
    dna.tr(@dna_nuc, @rna_nuc)
  end

  def self.of_rna(rna)
    rna.tr(@rna_nuc, @dna_nuc)
  end

end

puts Complement.of_dna 'ACGTGGTCTTAA'

