module Complement
  @@rna_to_dna = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  @@dna_to_rna = @@rna_to_dna.invert

  def self.of_dna(rna)
    rna.chars.map { |nucl| @@rna_to_dna[nucl] }.join
  end

  def self.of_rna(dna)
    dna.chars.map { |nucl| @@dna_to_rna[nucl] }.join
  end
end
