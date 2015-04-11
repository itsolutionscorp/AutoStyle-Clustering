module Complement

  def self.of_rna rna
    translate(rna, :to_dna)
  end

  def self.of_dna dna
    translate(dna, :to_rna)
  end

  private

  COMPLEMENTS = {
    to_rna: (TO_RNA = { 'T' => 'A', 'A' => 'U', 'G' => 'C', 'C' => 'G' }),
    # to_dna: { 'U' => 'A', 'A' => 'T', 'G' => 'C', 'C' => 'G' }
    to_dna: TO_RNA.invert
  }

  def self.translate chain, direction
    chain.chars.map { |n| COMPLEMENTS[direction][n] }.join
  end

end
