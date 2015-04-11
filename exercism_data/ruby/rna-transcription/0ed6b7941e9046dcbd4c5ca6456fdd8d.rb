module Complement
  COMPLEMENTS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(dna)
    dna.chars.map { |nucleotid| COMPLEMENTS[nucleotid] }.join('')
  end

  def self.of_rna(rna)
    rna.chars.map { |nucleotid| COMPLEMENTS.invert[nucleotid] }.join('')
  end
end
