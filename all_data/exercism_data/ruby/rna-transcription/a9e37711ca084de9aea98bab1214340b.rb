require 'pry'
class Complement
  @@dna_to_rna = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  @@rna_to_dna = @@dna_to_rna.invert

  def self.of_dna(dna)
    dna.chars.map { |c| @@dna_to_rna[c] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |c| @@rna_to_dna[c] }.join
  end
end
